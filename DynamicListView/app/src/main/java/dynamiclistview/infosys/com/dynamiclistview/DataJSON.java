package dynamiclistview.infosys.com.dynamiclistview;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Abhinav_Kumar21 on 3/24/2017.
 *
 * DataJSON.java - a model class for storing JSON response
 * @author  Abhinav_Kumar21
 * @version 1.0
 * @Description This model call contain structure data to store JSON response.
 *
 *
 */

public class DataJSON {

   // @SerializedName("title")
    String title;

   // @SerializedName("rows")
    RowElement[] rows;


    public String getTitle() {
        return title;
    }

    public RowElement[] getRows() {
        return rows;
    }
}
