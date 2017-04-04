package com.infosys.dynamicview.uicomponent;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.infosys.dynamicview.R;
import com.infosys.dynamicview.apisutil.Util;
import com.infosys.dynamicview.model.DataJSON;
import com.infosys.dynamicview.model.RowElement;
import com.infosys.dynamicview.remote.RequestInterface;
import com.infosys.dynamicview.remote.RetrofitClientCall;
import com.infosys.dynamicview.uiadapters.DataAdapter;

import junit.framework.Assert;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

import static org.hamcrest.Matchers.contains;
import static org.junit.matchers.JUnitMatchers.*;

import static org.junit.Assert.*;

/**
 * Created by Abhinav_Kumar21 on 4/3/2017.
 */
public class RecyclerViewFragmentTest extends Fragment {

    RecyclerViewFragment mFragment;
    private RecyclerView recyclerView;
    private ArrayList<RowElement> data;
    private SwipeRefreshLayout swipeContainer;
    private TextView mEmptyTextView;
    private ConnectivityManager connectivityManager;
    private DataAdapter adapter;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mFragment = new RecyclerViewFragment();
        mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().replace(0, mFragment, null).commit();
    }

    @Test
    public void testOnCreateView() throws Exception {

        mEmptyTextView = (TextView) mFragment.getView().findViewById(R.id.emptyTextView);
        Assert.assertNotNull(mEmptyTextView);

        recyclerView = (RecyclerView) mFragment.getView().findViewById(R.id.card_recycler_view);
        Assert.assertNotNull(recyclerView);

        //check for network connectivity
        connectivityManager = getConnectivityManager();
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        assertTrue(activeInfo != null && activeInfo.isConnected());

        //ArrayTest
        // assertThat(new ArrayList<>(), IsEmptyCollection.empty());


        //JSON Test
        String json = "{ {\n" +
                "\t\"title\":\"Beavers\",\n" +
                "\t\"description\":\"Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony\",\n" +
                "\t\"imageHref\":\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\"title\":\"Flag\",\n" +
                "\t\"description\":null,\n" +
                "\t\"imageHref\":\"http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png\"\n" +
                "\t}, }";

        RowElement elements = new Gson().fromJson(json, RowElement.class);

        // more assertions based on what the JSON was would be here
        assertThat(elements, (Matcher) contains("Beavers"));
        assertThat(elements, (Matcher) contains("description"));
        assertThat(elements, (Matcher) contains("imageHref"));


        //Retrofit Call check
        RequestInterface request = RetrofitClientCall.getClient(Util.BASE_URL).create(RequestInterface.class);
        Assert.assertNotNull(request);
    }

    private ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) mFragment.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Test
    public void testOnCreate() throws Exception {

    }

    @Test
    public void testOnResume() throws Exception {

    }

    @Test
    public void testOnActivityCreated() throws Exception {

    }

    @Test
    public void testRefreshItems() throws Exception {

    }

    @Test
    public void testOnItemsLoadComplete() throws Exception {

    }

    @Test
    public void testShowLoading() throws Exception {

    }

}