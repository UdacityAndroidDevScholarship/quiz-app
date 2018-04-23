package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {

    @Expose
    @SerializedName("timestamp")
    private long mTimestamp;
    @Expose
    @SerializedName("title")
    private String mTitle;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("from")
    private String mFrom;
    @Expose
    @SerializedName("type")
    private String mType;
    @Expose
    @SerializedName("action")
    private String mAction;
    @Expose
    @SerializedName("extra1")
    private String mExtra1;
    @Expose
    @SerializedName("extra2")
    private String mExtra2;

    public void setTimestamp(long timestamp) {
        this.mTimestamp = timestamp;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setFrom(String from) {
        this.mFrom = from;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public void setAction(String action) {
        this.mAction = action;
    }

    public void setExtra1(String extra1) {
        this.mExtra1 = extra1;
    }

    public void setExtra2(String extra2) {
        this.mExtra2 = extra2;
    }



    public long getmTimestamp() {
        return mTimestamp;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmFrom() {
        return mFrom;
    }

    public String getmType() {
        return mType;
    }

    public String getmAction() {
        return mAction;
    }

    public String getmExtra1() {
        return mExtra1;
    }

    public String getmExtra2() {
        return mExtra2;
    }


}
