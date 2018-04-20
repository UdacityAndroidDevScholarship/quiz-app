
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a User's notification preferences
 */
@IgnoreExtraProperties
public class NotificationPrefs {

    @Expose
    @SerializedName("comment-notifs")
    private Boolean mCommentNotifs;

    @Expose
    @SerializedName("member-messages")
    private Boolean mMemberMessages;

    @Expose
    @SerializedName("moderator-messages")
    private Boolean mModeratorMessages;

    @Expose
    @SerializedName("quiz-notifs")
    private Boolean mQuizNotifs;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

    public Boolean getCommentNotifs() {
        return mCommentNotifs;
    }

    public void setCommentNotifs(Boolean commentNotifs) {
        mCommentNotifs = commentNotifs;
    }

    public Boolean getMemberMessages() {
        return mMemberMessages;
    }

    public void setMemberMessages(Boolean memberMessages) {
        mMemberMessages = memberMessages;
    }

    public Boolean getModeratorMessages() {
        return mModeratorMessages;
    }

    public void setModeratorMessages(Boolean moderatorMessages) {
        mModeratorMessages = moderatorMessages;
    }

    public Boolean getQuizNotifs() {
        return mQuizNotifs;
    }

    public void setQuizNotifs(Boolean quizNotifs) {
        mQuizNotifs = quizNotifs;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

}
