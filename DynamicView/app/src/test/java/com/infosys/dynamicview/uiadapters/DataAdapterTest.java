package com.infosys.dynamicview.uiadapters;

import android.support.test.rule.ActivityTestRule;
import android.widget.ImageView;
import android.widget.TextView;

import com.infosys.dynamicview.R;
import com.infosys.dynamicview.model.RowElement;
import com.infosys.dynamicview.uicomponent.MainActivity;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Abhinav_Kumar21 on 4/4/2017.
 */
public class DataAdapterTest {

    private ArrayList<RowElement> arrRowElement;
    private TextView tv_title, tv_description;
    private ImageView img_url_ref;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onBindViewHolder() throws Exception {

        tv_title = (TextView) mActivityRule.getActivity().findViewById(R.id.tv_rowtitle);
        Assert.assertNotNull(tv_title);

        tv_description = (TextView) mActivityRule.getActivity().findViewById(R.id.tv_rowdescription);
        Assert.assertNotNull(tv_description);

        img_url_ref = (ImageView) mActivityRule.getActivity().findViewById(R.id.img_url);
        Assert.assertNotNull(img_url_ref);

    }
}