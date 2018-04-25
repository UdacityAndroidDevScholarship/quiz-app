package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.gson.annotations.SerializedName;

/*
Model class representing Notification
 */

public class Notification {

    @SerializedName("extra2")
    private String extra2;

    @SerializedName("description")
    private String description;

    @SerializedName("action")
    private String action;

    @SerializedName("from")
    private String from;

    @SerializedName("extra1")
    private String extra1;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private String type;

    @SerializedName("timestamp")
    private int timestamp;

    public void setExtra2(String extra2){
        this.extra2 = extra2;
    }

    public String getExtra2(){
        return extra2;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setAction(String action){
        this.action = action;
    }

    public String getAction(){
        return action;
    }

    public void setFrom(String from){
        this.from = from;
    }

    public String getFrom(){
        return from;
    }

    public void setExtra1(String extra1){
        this.extra1 = extra1;
    }

    public String getExtra1(){
        return extra1;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setTimestamp(int timestamp){
        this.timestamp = timestamp;
    }

    public int getTimestamp(){
        return timestamp;
    }

}