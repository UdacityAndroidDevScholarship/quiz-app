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

    public void setmTimeStamp(long mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmFrom(String mFrom) {
        this.mFrom = mFrom;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public void setmAction(String mAction) {
        this.mAction = mAction;
    }

    public void setmExtra1(String mExtra1) {
        this.mExtra1 = mExtra1;
    }

    public void setmExtra2(String mExtra2) {
        this.mExtra2 = mExtra2;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    public String getmKey() {

        return mKey;
    }
}
