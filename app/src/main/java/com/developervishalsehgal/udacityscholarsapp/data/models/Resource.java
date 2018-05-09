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
    @PropertyName("shared-by")
    @SerializedName("shared-by")
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

    @Exclude
    public String getCategory() {
        return mCategory;
    }

    @Exclude
    public void setCategory(String category) {
        this.mCategory = category;
    }

    @Exclude
    public String getDescription() {
        return mDescription;
    }

    @Exclude
    public void setDescription(String description) {
        this.mDescription = description;
    }

    @Exclude
    public String getTags() {
        return mTags;
    }

    @Exclude
    public void setTags(String tags) {
        this.mTags = tags;
    }

    @Exclude
    public String getTitle() {
        return mTitle;
    }

    @Exclude
    public void setTitle(String title) {
        this.mTitle = title;
    }

    @Exclude
    public String getUrl() {
        return mUrl;
    }

    @Exclude
    public void setUrl(String url) {
        this.mUrl = url;
    }

    @Exclude
    public long getTimestamp() {
        return mTimestamp;
    }

    @Exclude
    public void setTimestamp(long timestamp) {
        this.mTimestamp = timestamp;
    }

    @Exclude
    public String getUploadedBy() {
        return mUploadedBy;
    }

    @Exclude
    public void setUploadedBy(String uploadedBy) {
        this.mUploadedBy = uploadedBy;
    }

    @Exclude
    public String getUploaderId() {
        return mUploaderId;
    }

    @Exclude
    public void setUploaderId(String uploaderId) {
        this.mUploaderId = uploaderId;
    }

    @Exclude
    public String getUploaderPic() {
        return mUploaderPic;
    }

    @Exclude
    public void setUploaderPic(String uploaderPic) {
        this.mUploaderPic = uploaderPic;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        this.mKey = key;
    }
}
