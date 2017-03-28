package dynamiclistview.infosys.com.dynamiclistview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Abhinav_Kumar21 on 3/27/2017.
 *
 * Util.java
 * @author  Abhinav_Kumar21
 * @version 1.0
 * @Description This class contain user defined variables and network error messages
 *
 *
 */
public class Util {

    public static final String BASE_URL = "https://dl.dropboxusercontent.com/s/";
    public static final String ENDPOINT_URL = "2iodh4vg0eortkl/facts.json";
    private static String NETWORK_ERROR_HEADING = "Network Error";
    private static String NETWORK_ERROR_MESSAGE = "You are not connected to network. Please change mobile network settings and try again.";
    public static int IMG_RESIZE_VALUE = 500;//this is a temporary value required to set for showing image fit in recycleview row item


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showErrorMessage(final Context context){

        new AlertDialog.Builder(context)
                .setTitle(NETWORK_ERROR_HEADING)
                .setMessage(NETWORK_ERROR_MESSAGE)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .show();

    }



}
