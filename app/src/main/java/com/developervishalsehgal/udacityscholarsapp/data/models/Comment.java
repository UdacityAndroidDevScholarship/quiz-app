
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@IgnoreExtraProperties
public class Comment {

    @Expose
    @PropertyName("comment")
    @SerializedName("comment")
    String mComment;

    @Expose
    @PropertyName("comment-by")
    @SerializedName("comment-by")
    String mCommentBy;

    @Expose
    @PropertyName("commented-on")
    @SerializedName("commented-on")
    long mCommentedOn;

    @Expose
    @PropertyName("commenter-id")
    @SerializedName("commenter-id")
    String mCommenterId;

    @Expose
    @PropertyName("commenter-pic")
    @SerializedName("commenter-pic")
    String mImageUrl;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    String mKey;

    @Exclude
    public String getComment() {
        return mComment;
    }

    @Exclude
    public void setComment(String comment) {
        mComment = comment;
    }

    @Exclude
    public String getCommentBy() {
        return mCommentBy;
    }

    @Exclude
    public void setCommentBy(String commentBy) {
        mCommentBy = commentBy;
    }

    @Exclude
    public long getCommentedOn() {
        return mCommentedOn;
    }

    @Exclude
    public void setCommentedOn(long commentedOn) {
        mCommentedOn = commentedOn;
    }

    @Exclude
    public String getCommenterId() {
        return mCommenterId;
    }

    @Exclude
    public void setCommenterId(String commenterId) {
        mCommenterId = commenterId;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        this.mKey = key;
    }

    @Exclude
    public String getImage() {
        return mImageUrl;
    }

    @Exclude
    public void setImage(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return mCommentedOn == comment.mCommentedOn &&
                Objects.equals(mComment, comment.mComment) &&
                Objects.equals(mCommentBy, comment.mCommentBy) &&
                Objects.equals(mCommenterId, comment.mCommenterId) &&
                Objects.equals(mImageUrl, comment.mImageUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mComment, mCommentBy, mCommentedOn, mCommenterId);
    }
}
