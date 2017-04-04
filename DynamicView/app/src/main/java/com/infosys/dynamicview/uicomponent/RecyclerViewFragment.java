package com.infosys.dynamicview.uicomponent;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infosys.dynamicview.remote.RequestInterface;
import com.infosys.dynamicview.apisutil.Util;
import com.infosys.dynamicview.model.DataJSON;
import com.infosys.dynamicview.model.RowElement;
import com.infosys.dynamicview.uiadapters.DataAdapter;
import com.infosys.dynamicview.R;
import com.infosys.dynamicview.remote.RetrofitClientCall;

import java.util.ArrayList;

import javax.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abhinav_Kumar21 on 3/27/2017.
 * <p/>
 * RecyclerViewFragment.java
 *
 * @author Abhinav_Kumar21
 * @version 1.0
 * @Description This is class which act as reusable fragment for main view
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<RowElement> data;
    private DataAdapter adapter;
    private SwipeRefreshLayout swipeContainer;
    private TextView mEmptyTextView;
    ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycleview, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        // Lookup the swipe container view

        swipeContainer = (SwipeRefreshLayout) getView().findViewById(com.infosys.dynamicview.R.id.swipeContainer);
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
        recyclerView.invalidate();
        retrieveJSONDataGet();
        //retrieveJSONDataGet();

        // Load complete
        onItemsLoadComplete();
    }

    /*
       This completes pull to refresh
    */

    void onItemsLoadComplete() {
        // Stop refresh animation
        swipeContainer.setRefreshing(false);
    }

    /*
       This Function initiate and set Views
       It checks Network Availability and Notify user of connection related Behavior
     */
    private void initializeView() {

        mEmptyTextView = (TextView) getView().findViewById(com.infosys.dynamicview.R.id.emptyTextView);

        recyclerView = (RecyclerView) getView().findViewById(com.infosys.dynamicview.R.id.card_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(layoutManager);

        if (Util.isNetworkAvailable(this.getContext())) {
            retrieveJSONDataGet();
        } else {
            Util.showErrorMessage(this.getContext(), getContext().getString(R.string.network_error_heading), getContext().getString(R.string.network_error_message));
        }
    }

    /*
       Here initialize Retrofit and carry out network operations.
       RequestInterface object is created
       Asyncronous request Call object should be created from RequestInterface object
       onResponse()/onFailure callback are excuted for Async call
  */
    private void retrieveJSONDataGet() {

        showLoading();
        RequestInterface request = RetrofitClientCall.getClient(getAppContext().getString(R.string.base_url)).create(RequestInterface.class);
        try {
            Call<DataJSON> call = request.getJSON();
            call.enqueue(new Callback<DataJSON>() {
                @Override
                public void onResponse(Call<DataJSON> call, Response<DataJSON> response) {
                    disableLoading();
                    if (response.body() != null) {

                        DataJSON jsonResponse = response.body();
                        MainActivity.tv_titlebar.setText(jsonResponse.getTitle());//setup Titlebar Text
                        data = new ArrayList<RowElement>(jsonResponse.getRows());
                        adapter = new DataAdapter(getAppContext(), data);
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
                       } else {
                        Util.showErrorMessage(getAppContext(), getContext().getString(R.string.unknown_error_heading), getContext().getString(R.string.unknown_error_message));
                    }

                }

                @Override
                public void onFailure(Call<DataJSON> call, Throwable t) {

                    Log.e("", "message =" + t.getMessage());
                    disableLoading();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /*
       This checks whether list to be shown is empty
    */
    private void checkRecyclerViewIsEmpty() {
        disableLoading();

        if (adapter.getItemCount() == 0) {
            mEmptyTextView.setVisibility(View.VISIBLE);
        } else {
            mEmptyTextView.setVisibility(View.GONE);
        }
    }

    void showLoading() {
        //Progressbar
        mProgressDialog = new ProgressDialog(this.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

    }

    void disableLoading() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();

    }

    public Context getAppContext() {
        return this.getContext();
    }


}
