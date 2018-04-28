
package com.developervishalsehgal.udacityscholarsapp.data.models;

<<<<<<< HEAD
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

=======
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private String mCommentedOn;

    @Expose
    @SerializedName("commenter-id")
    private String mCommenterId;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

>>>>>>> upstream/development
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

<<<<<<< HEAD
=======
    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }
>>>>>>> upstream/development
}
