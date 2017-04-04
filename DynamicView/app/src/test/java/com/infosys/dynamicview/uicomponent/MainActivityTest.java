package com.infosys.dynamicview.uicomponent;

import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import com.infosys.dynamicview.R;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Abhinav_Kumar21 on 4/3/2017.
 */
public class MainActivityTest {


    private MainActivity testingActivity;
    private RecyclerViewFragment testFragment;
    public static TextView tv_titlebar;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        testFragment = new RecyclerViewFragment();
        mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().replace(0, testFragment, null).commit();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnCreate() throws Exception {
       //Title Bar check
        tv_titlebar = (TextView) mActivityRule.getActivity().findViewById(R.id.titlebartext);
        Assert.assertNotNull(tv_titlebar);
   }
}
