package com.infosys.dynamicview.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abhinav_Kumar21 on 3/24/2017.
 * <p/>
 * DataJSON.java - a model class for storing JSON response
 *
 * @author Abhinav_Kumar21
 * @version 1.0
 * @Description This model call contain structure data to store JSON response.
 */

public class DataJSON {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("rows")
    @Expose
    private List<RowElement> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RowElement> getRows() {
        return rows;
    }

    public void setRows(List<RowElement> rows) {
        this.rows = rows;
    }
}
