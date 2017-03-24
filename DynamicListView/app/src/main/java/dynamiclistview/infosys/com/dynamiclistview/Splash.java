package com.infosys.com.dynamiclistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dynamiclistview.infosys.com.dynamiclistview.MainActivity;


public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }



}
