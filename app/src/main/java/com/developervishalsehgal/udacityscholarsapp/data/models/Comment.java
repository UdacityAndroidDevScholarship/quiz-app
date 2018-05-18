
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
    @SerializedName("comment")
    private String mComment;

    @Expose
    @SerializedName("comment-by")
    private String mCommentBy;

    @Expose
    @SerializedName("commented-on")
    private long mCommentedOn;

    @Expose
    @SerializedName("commenter-id")
    private String mCommenterId;

    @Expose
    @SerializedName("commenter-pic")
    private String mImageUrl;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

    @Exclude
    private boolean mIsMyComment;

    @PropertyName("comment")
    public String getComment() {
        return mComment;
    }

    @PropertyName("comment")
    public void setComment(String comment) {
        mComment = comment;
    }

    @PropertyName("comment-by")
    public String getCommentBy() {
        return mCommentBy;
    }

    @PropertyName("comment-by")
    public void setCommentBy(String commentBy) {
        mCommentBy = commentBy;
    }

    @PropertyName("commented-on")
    public long getCommentedOn() {
        return mCommentedOn;
    }

    @PropertyName("commented-on")
    public void setCommentedOn(long commentedOn) {
        mCommentedOn = commentedOn;
    }

    @PropertyName("commenter-id")
    public String getCommenterId() {
        return mCommenterId;
    }

    @PropertyName("commenter-id")
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

    @PropertyName("commenter-pic")
    public String getImage() {
        return mImageUrl;
    }

    @PropertyName("commenter-pic")
    public void setImage(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    @Exclude
    public boolean isMyComment() {
        return mIsMyComment;
    }

    @Exclude
    public void setMyComment(boolean myComment) {
        mIsMyComment = myComment;
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
