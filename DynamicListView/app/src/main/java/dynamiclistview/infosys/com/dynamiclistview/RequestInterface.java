package dynamiclistview.infosys.com.dynamiclistview;

/**
 * Created by Abhinav_Kumar21 on 3/24/2017.
 *
 * RequestInterface.java - Interface
 * @author  Abhinav_Kumar21
 * @version 1.0
 * @Description Interface that defines URL endpoint and get JSON Object
 *
 *
 */

import retrofit2.Call;
import retrofit2.http.GET;


public interface RequestInterface {

    //The endpoint is defined using @GET annotation
    @GET(Util.ENDPOINT_URL)



    //For our request method getJSON() the DataJSON object is wrapped in Call object.
    // By using Call, the request is made Asynchronous so you need not worry about UI blocking or AsyncTask.
    // The JSON response received after making the request it is stored in DataJSON object
    Call<DataJSON> getJSON();


}
