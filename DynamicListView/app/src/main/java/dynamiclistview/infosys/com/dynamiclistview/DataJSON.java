package dynamiclistview.infosys.com.dynamiclistview;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhinav_Kumar21 on 3/26/2017.
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
