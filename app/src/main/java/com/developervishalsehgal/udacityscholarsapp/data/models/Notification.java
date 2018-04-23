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

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getFrom() {
        return mFrom;
    }

    public String getType() {
        return mType;
    }

    public String getAction() {
        return mAction;
    }

    public String getExtra1() {
        return mExtra1;
    }

    public String getExtra2() {
        return mExtra2;
    }

    public void setTimeStamp(long timeStamp) {
        this.mTimeStamp = timeStamp;
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

    public void setKey(String key) {
        this.mKey = key;
    }

    public String getKey() {

        return mKey;
    }
}
