package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationPojo {

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

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

    public long getTimestamp() {
        return mTimestamp;
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

    public String getKey() {
        return mKey;
    }

    public void setTimestamp(long timestamp) {
        mTimestamp = timestamp;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setFrom(String from) {
        mFrom = from;
    }

    public void setType(String type) {
        mType = type;
    }

    public void setAction(String action) {
        mAction = action;
    }

    public void setExtra1(String extra1) {
        mExtra1 = extra1;
    }

    public void setExtra2(String extra2) {
        mExtra2 = extra2;
    }

    public void setKey(String key) {
        mKey = key;
    }
}
