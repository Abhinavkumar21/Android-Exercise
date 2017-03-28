package dynamiclistview.infosys.com.dynamiclistview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhinav_Kumar21 on 3/24/2017.
 *
 * MainActivity.java - a simple Activity class for entry point of Application.
 * @author  Abhinav_Kumar21
 * @version 1.0
 * @Description This class is the Activity Class which has Base call for all Related classes - TitleBar, Network,
 *              RetroFit.
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<RowElement> data;
    private DataAdapter adapter;
    private SwipeRefreshLayout swipeContainer;
    private TextView tv_titlebar,mEmptyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Request for custom title bar
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        //set to your layout file
        setContentView(R.layout.activity_main);

        //Hide the default Titlebar defaults with AppCompatActivity to override with custom Titlebar
        getSupportActionBar().hide();

        //Set the titlebar layout
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_titlebar);

        //Read the value for setting JSON text in TitleBar
        tv_titlebar = (TextView)findViewById(R.id.titlebartext);

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        //pull to refresh functions
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        initializeView();
    }



   /*
       This refreshes the items on pull to refresh
    */

    void refreshItems() {
        // Load items
        // ...

        retrieveJSONDataGet();

        // Load complete
        onItemsLoadComplete();
    }

    /*
       This completes pull to refresh
    */

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        swipeContainer.setRefreshing(false);
    }

    /*
       This Function initiate and set Views
       It checks Network Availability and Notify user of connection related Behavior
     */
    private void initializeView(){

        mEmptyTextView = (TextView) findViewById(R.id.emptyTextView);

        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        //recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        if(Util.isNetworkAvailable(getApplicationContext())) {
            retrieveJSONDataGet();
        }
        else
        {
            Util.showErrorMessage(this);
        }
    }

    /*
       Here initialize Retrofit and carry out network operations.
       RequestInterface object is created
       Asyncronous request Call object should be created from RequestInterface object
       onResponse()/onFailure callback are excuted for Async call
  */
    private void retrieveJSONDataGet(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Util.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<DataJSON> call = request.getJSON();
        call.enqueue(new Callback<DataJSON>() {
            @Override
            public void onResponse(Call<DataJSON> call, Response<DataJSON> response) {

                DataJSON jsonResponse = response.body();
                tv_titlebar.setText(jsonResponse.getTitle());//setup Titlebar Text
                data = new ArrayList<>(Arrays.asList(jsonResponse.getRows()));
                adapter = new DataAdapter(getApplicationContext(), data);


                adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();

                        checkRecyclerViewIsEmpty();
                    }

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount) {
                        super.onItemRangeChanged(positionStart, itemCount);

                        checkRecyclerViewIsEmpty();
                    }

                    @Override
                    public void onItemRangeRemoved(int positionStart, int itemCount) {
                        super.onItemRangeRemoved(positionStart, itemCount);

                        checkRecyclerViewIsEmpty();
                    }
                });


                recyclerView.setAdapter(adapter);
               // checkRecyclerViewIsEmpty();


            }

            @Override
            public void onFailure(Call<DataJSON> call, Throwable t) {

            }
        });
    }

   /*
      This checks whether list to be shown is empty

    */
    private void checkRecyclerViewIsEmpty() {
        if (adapter.getItemCount() == 0) {
            mEmptyTextView.setVisibility(View.VISIBLE);
        } else {
            mEmptyTextView.setVisibility(View.GONE);
        }
    }


}




