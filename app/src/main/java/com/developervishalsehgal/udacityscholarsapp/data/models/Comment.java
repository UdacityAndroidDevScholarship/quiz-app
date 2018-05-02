
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

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getCommentBy() {
        return mCommentBy;
    }

    public void setCommentBy(String commentBy) {
        mCommentBy = commentBy;
    }

    public long getCommentedOn() {
        return mCommentedOn;
    }

    public void setCommentedOn(long commentedOn) {
        mCommentedOn = commentedOn;
    }

    public String getCommenterId() {
        return mCommenterId;
    }

    public void setCommenterId(String commenterId) {
        mCommenterId = commenterId;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

    public String getImage() {
        return mImageUrl;
    }

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
