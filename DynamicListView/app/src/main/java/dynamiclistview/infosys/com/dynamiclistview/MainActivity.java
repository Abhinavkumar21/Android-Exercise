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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhinav_Kumar21 on 3/24/2017.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<RowElement> data;
    private DataAdapter adapter;
    private SwipeRefreshLayout swipeContainer;
    private TextView tv_titlebar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Request for custom title bar
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //set to your layout file
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        // Lookup the swipe container viewt
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        //Set the titlebar layout
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_titlebar);
        tv_titlebar = (TextView)findViewById(R.id.titlebartext);

        initViews();
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    void refreshItems() {
        // Load items
        // ...

        loadJSON();

        // Load complete
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        swipeContainer.setRefreshing(false);
    }
    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        if(isNetworkAvailable())
             loadJSON();
        else
        {

            new AlertDialog.Builder(this)
                    .setTitle("Network Error")
                    .setMessage("You are not connected to network. Please change mobile network settings and try again.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            finish();
                        }
                    })
                    .show();
        }
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com/s/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<DataJSON> call = request.getJSON();
        call.enqueue(new Callback<DataJSON>() {
            @Override
            public void onResponse(Call<DataJSON> call, Response<DataJSON> response) {

                System.out.println("onResponse ::" + new Gson().toJson(response.body()));

                //JSONResponse res = response.body();

                DataJSON jsonResponse = response.body();
                System.out.println("Title ::" + new Gson().toJson(jsonResponse.getTitle()));
                System.out.println("Title ::" + tv_titlebar);
                tv_titlebar.setText(jsonResponse.getTitle());
                data = new ArrayList<>(Arrays.asList(jsonResponse.getRows()));
                adapter = new DataAdapter(getApplicationContext(),data);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<DataJSON> call, Throwable t) {

            }
        });
    }
}




