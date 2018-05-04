package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a resource (URL to a blog, article, tutorial etc.)
 */
@IgnoreExtraProperties
public class Resource {

    @Expose
    @PropertyName("category")
    @SerializedName("category")
    String mCategory;

    @Expose
    @PropertyName("description")
    @SerializedName("description")
    String mDescription;

    @Expose
    @PropertyName("tags")
    @SerializedName("tags")
    String mTags;

    @Expose
    @PropertyName("title")
    @SerializedName("title")
    String mTitle;

    @Expose
    @PropertyName("url")
    @SerializedName("url")
    String mUrl;

    @Expose
    @PropertyName("timestamp")
    @SerializedName("timestamp")
    long mTimestamp;

    @Expose
    @PropertyName("uploaded-by")
    @SerializedName("uploaded-by")
    String mUploadedBy;

    @Expose
    @PropertyName("uploader-id")
    @SerializedName("uploader-id")
    String mUploaderId;

    @Expose
    @PropertyName("uploader-pic")
    @SerializedName("uploader-pic")
    String mUploaderPic;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    String mKey;

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getTags() {
        return mTags;
    }

    public void setTags(String tags) {
        this.mTags = tags;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(long timestamp) {
        this.mTimestamp = timestamp;
    }

    public String getUploadedBy() {
        return mUploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.mUploadedBy = uploadedBy;
    }

    public String getUploaderId() {
        return mUploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.mUploaderId = uploaderId;
    }

    public String getUploaderPic() {
        return mUploaderPic;
    }

    public void setUploaderPic(String uploaderPic) {
        this.mUploaderPic = uploaderPic;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }
}
