package com.developervishalsehgal.udacityscholarsapp.data.models;

public class Notification {
    private long mTimestamp;
    private String mTitle;
    private String mDescription;
    private String mFrom;
    private String mType;
    private String mAction;
    private String mExtra1;
    private String mExtra2;

    public void setmTimestamp(long timestamp) {
        this.mTimestamp = timestamp;
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
