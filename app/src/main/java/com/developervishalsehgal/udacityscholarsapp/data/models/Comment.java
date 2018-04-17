
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("comment")
    private String mComment;

    @SerializedName("comment-by")
    private String mCommentBy;

    @SerializedName("commented-on")
    private String mCommentedOn;

    @SerializedName("commenter-id")
    private String mCommenterId;

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

    public String getCommentedOn() {
        return mCommentedOn;
    }

    public void setCommentedOn(String commentedOn) {
        mCommentedOn = commentedOn;
    }

    public String getCommenterId() {
        return mCommenterId;
    }

    public void setCommenterId(String commenterId) {
        mCommenterId = commenterId;
    }

}
