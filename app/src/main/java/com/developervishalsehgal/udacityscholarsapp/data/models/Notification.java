package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@IgnoreExtraProperties
public class Notification {


    @Expose
    @SerializedName("time-stamp")
    private long mTimeStamp;

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

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

    public long getmTimeStamp() {
        return mTimeStamp;
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

    public void setmTimeStamp(long timeStamp) {
        this.mTimeStamp = timeStamp;
    }

    public void setmTitle(String title) {
        this.mTitle = title;
    }

    public void setmDescription(String description) {
        this.mDescription = description;
    }

    public void setmFrom(String from) {
        this.mFrom = from;
    }

    public void setmType(String type) {
        this.mType = type;
    }

    public void setmAction(String action) {
        this.mAction = action;
    }

    public void setmExtra1(String extra1) {
        this.mExtra1 = extra1;
    }

    public void setmExtra2(String extra2) {
        this.mExtra2 = extra2;
    }

    public void setmKey(String key) {
        this.mKey = key;
    }

    public String getmKey() {

        return mKey;
    }
}
