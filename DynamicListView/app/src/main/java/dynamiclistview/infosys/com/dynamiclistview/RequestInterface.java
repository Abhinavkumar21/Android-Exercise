package dynamiclistview.infosys.com.dynamiclistview;

/**
 * Created by Abhinav_Kumar21 on 3/24/2017.
 */

import retrofit2.Call;
import retrofit2.http.GET;


public interface RequestInterface {

    @GET("2iodh4vg0eortkl/facts.json")
    Call<DataJSON> getJSON();


}
