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
    @SerializedName("category")
    private String mCategory;

    @Expose
    @SerializedName("description")
    private String mDescription;

    @Expose
    @SerializedName("tags")
    private String mTags;

    @Expose
    @SerializedName("title")
    private String mTitle;

    @Expose
    @SerializedName("url")
    private String mUrl;

    @Expose
    @SerializedName("timestamp")
    private long mTimestamp;

    @Expose
    @SerializedName("shared-by")
    private String mUploadedBy;

    @Expose
    @SerializedName("uploader-id")
    private String mUploaderId;

    @Expose
    @SerializedName("uploader-pic")
    private String mUploaderPic;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

    @PropertyName("category")
    public String getCategory() {
        return mCategory;
    }

    @PropertyName("category")
    public void setCategory(String category) {
        this.mCategory = category;
    }

    @PropertyName("description")
    public String getDescription() {
        return mDescription;
    }

    @PropertyName("description")
    public void setDescription(String description) {
        this.mDescription = description;
    }

    @PropertyName("tags")
    public String getTags() {
        return mTags;
    }

    @PropertyName("tags")
    public void setTags(String tags) {
        this.mTags = tags;
    }

    @PropertyName("title")
    public String getTitle() {
        return mTitle;
    }

    @PropertyName("title")
    public void setTitle(String title) {
        this.mTitle = title;
    }

    @PropertyName("url")
    public String getUrl() {
        return mUrl;
    }

    @PropertyName("url")
    public void setUrl(String url) {
        this.mUrl = url;
    }

    @PropertyName("timestamp")
    public long getTimestamp() {
        return mTimestamp;
    }

    @PropertyName("timestamp")
    public void setTimestamp(long timestamp) {
        this.mTimestamp = timestamp;
    }

    @PropertyName("shared-by")
    public String getUploadedBy() {
        return mUploadedBy;
    }

    @PropertyName("shared-by")
    public void setUploadedBy(String uploadedBy) {
        this.mUploadedBy = uploadedBy;
    }

    @PropertyName("uploader-id")
    public String getUploaderId() {
        return mUploaderId;
    }

    @PropertyName("uploader-id")
    public void setUploaderId(String uploaderId) {
        this.mUploaderId = uploaderId;
    }

    @PropertyName("uploader-pic")
    public String getUploaderPic() {
        return mUploaderPic;
    }

    @PropertyName("uploader-pic")
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
