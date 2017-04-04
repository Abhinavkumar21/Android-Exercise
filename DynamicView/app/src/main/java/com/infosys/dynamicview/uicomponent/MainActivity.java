package com.infosys.dynamicview.uicomponent;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.infosys.dynamicview.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhinav_Kumar21 on 3/24/2017.
 * <p>
 * MainActivity.java - a simple Activity class for entry point of Application.
 *
 * @author Abhinav_Kumar21
 * @version 1.0
 * @Description This class is the Activity Class which has Base call for all Related classes - TitleBar, Network,
 * RetroFit.
 */
public class MainActivity extends AppCompatActivity {

    public static TextView tv_titlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Request for custom title bar
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        //Hide the default Titlebar defaults with AppCompatActivity to override with custom Titlebar
        getSupportActionBar().hide();
        //Set the titlebar layout
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, com.infosys.dynamicview.R.layout.activity_titlebar);
        //Read the value for setting JSON text in TitleBar
        tv_titlebar = (TextView) findViewById(com.infosys.dynamicview.R.id.titlebartext);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            RecyclerViewFragment fragment = new RecyclerViewFragment();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }


    }

}





