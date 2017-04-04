package com.infosys.dynamicview.apisutil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Abhinav_Kumar21 on 3/27/2017.
 * <p/>
 * Util.java
 *
 * @author Abhinav_Kumar21
 * @version 1.0
 * @Description This class contain user defined variables and network error messages
 */
public class Util {

    public static final String ENDPOINT_URL = "2iodh4vg0eortkl/facts.json";
    public static int IMG_RESIZE_VALUE = 350;//this is a temporary value required to set for showing image fit in recycleview row item

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showErrorMessage(final Context context, String header, String meaageBody) {

        new AlertDialog.Builder(context)
                .setTitle(header)
                .setMessage(meaageBody)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity)context).finish();
                    }
                })
                .show();

    }
}
