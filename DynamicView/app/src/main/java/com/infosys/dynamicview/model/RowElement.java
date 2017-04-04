package com.infosys.dynamicview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhinav_Kumar21 on 3/26/2017.
 * <p/>
 * RowElement.java
 *
 * @author Abhinav_Kumar21
 * @version 1.0
 * @Description Simple model for JOSN structure reference.
 */

public class RowElement {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {

        this.imageHref = imageHref;
    }
}
