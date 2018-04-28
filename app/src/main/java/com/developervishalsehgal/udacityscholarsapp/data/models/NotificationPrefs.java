
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
    private boolean mCommentNotifs;

    @Expose
    @SerializedName("member-messages")
    private boolean mMemberMessages;

    @Expose
    @SerializedName("moderator-messages")
    private boolean mModeratorMessages;

    @Expose
    @SerializedName("quiz-notifs")
    private boolean mQuizNotifs;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

    public boolean getCommentNotifs() {
        return mCommentNotifs;
    }

    public void setCommentNotifs(boolean commentNotifs) {
        mCommentNotifs = commentNotifs;
    }

    public boolean getMemberMessages() {
        return mMemberMessages;
    }

    public void setMemberMessages(boolean memberMessages) {
        mMemberMessages = memberMessages;
    }

    public boolean getModeratorMessages() {
        return mModeratorMessages;
    }

    public void setModeratorMessages(boolean moderatorMessages) {
        mModeratorMessages = moderatorMessages;
    }

    public boolean getQuizNotifs() {
        return mQuizNotifs;
    }

    public void setQuizNotifs(boolean quizNotifs) {
        mQuizNotifs = quizNotifs;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

}
