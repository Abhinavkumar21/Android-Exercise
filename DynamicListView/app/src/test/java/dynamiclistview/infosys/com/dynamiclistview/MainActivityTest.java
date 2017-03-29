package dynamiclistview.infosys.com.dynamiclistview;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import junit.framework.Assert;

/**
 * Created by Abhinav_Kumar21 on 3/29/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    RecyclerView mRecycledView;
    TextView   titlebartext,emptytext;

    public MainActivityTest() {
        super();
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnCreate() throws Exception {



    }

    @Test
    public void testRefreshItems() throws Exception {

    }

    @Test
    public void testOnItemsLoadComplete() throws Exception {

        //Title Bar check
        titlebartext = (TextView) mActivityRule.getActivity().findViewById(R.id.titlebartext);
        Assert.assertNotNull(titlebartext);

        //Empty Text View
        emptytext = (TextView) mActivityRule.getActivity().findViewById(R.id.emptyTextView);
        Assert.assertNotNull(titlebartext);


        //recycle vew list check
        mRecycledView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.card_recycler_view);

        int expectedCount = 10;
        int actualCount = mRecycledView.getAdapter().getItemCount();
        Assert.assertEquals(expectedCount, actualCount);



    }
}