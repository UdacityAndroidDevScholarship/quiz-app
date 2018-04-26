package com.developervishalsehgal.udacityscholarsapp.data.models;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by nihanth_2 on 4/21/2018.
 */

@IgnoreExtraProperties
public class NotificationPojo {

    @Expose
    @SerializedName("timestamp")
    private long mTimestamp;

    @Expose
    @SerializedName("description")
    private String mDescription;

    @Expose
    @SerializedName("title")
    private String mTitle;

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

    public long getTimestamp(){
        return mTimestamp;
    }

    public String getDescription(){
        return mDescription;
    }

    public String gettitle(){
        return mTitle;
    }

    public String getfrom(){
        return mFrom;
    }

    public String getType(){
        return mType;
    }

    public String getExtra1(){
        return mExtra1;
    }

    public String getExtra2(){
        return mExtra2;
    }

    public String getAction(){
        return mAction;
    }

    public void setTimestamp(long timestamp){
        this.mTimestamp = timestamp;
    }

    public void setDescription(String description){
        this.mDescription = description;
    }

    public void settitle(String title){
        this.mTitle = title;
    }

    public void setfrom(String from){
        this.mFrom = from;
    }

    public void setType(String type){
        this.mType = type;
    }

    public void setExtra1(String extra1){
        this.mExtra1 = extra1;
    }

    public void setExtra2(String extra2){
        this.mExtra2 = extra2;
    }

    public void setAction(String action){
        this.mAction = action;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

}
