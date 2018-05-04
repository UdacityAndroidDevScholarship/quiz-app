package com.developervishalsehgal.udacityscholarsapp.data.models;

import java.util.Objects;

/**
 * Created by dell on 4/21/2018.
 */

public class Notification {

    private long mTimeStamp;
    private String mDescription;
    private String mTitle;
    private String mFrom;
    private String mType;
    private String mAction;
    private String mExtra1;
    private String mExtra2;

    //Getter Methods for Notification data
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

    //Setter Methods for Notification data
    public void setTimeStamp(long timestamp) {
        this.mTimeStamp = timestamp;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return mTimeStamp == that.mTimeStamp &&
                Objects.equals(mDescription, that.mDescription) &&
                Objects.equals(mTitle, that.mTitle) &&
                Objects.equals(mFrom, that.mFrom) &&
                Objects.equals(mType, that.mType) &&
                Objects.equals(mAction, that.mAction) &&
                Objects.equals(mExtra1, that.mExtra1) &&
                Objects.equals(mExtra2, that.mExtra2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mTimeStamp, mDescription, mTitle, mFrom, mType, mAction, mExtra1, mExtra2);
    }
}
