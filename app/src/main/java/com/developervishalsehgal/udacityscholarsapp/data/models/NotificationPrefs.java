
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a User's notification preferences
 */
@IgnoreExtraProperties
public class NotificationPrefs {

    @Expose
    @PropertyName("comment-notifs")
    @SerializedName("comment-notifs")
    boolean mCommentNotifs;

    @Expose
    @PropertyName("member-messages")
    @SerializedName("member-messages")
    boolean mMemberMessages;

    @Expose
    @PropertyName("moderator-messages")
    @SerializedName("moderator-messages")
    boolean mModeratorMessages;

    @Expose
    @PropertyName("quiz-notifs")
    @SerializedName("quiz-notifs")
    boolean mQuizNotifs;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    String mKey;

    @Exclude
    public boolean getCommentNotifs() {
        return mCommentNotifs;
    }

    @Exclude
    public void setCommentNotifs(boolean commentNotifs) {
        mCommentNotifs = commentNotifs;
    }

    @Exclude
    public boolean getMemberMessages() {
        return mMemberMessages;
    }

    @Exclude
    public void setMemberMessages(boolean memberMessages) {
        mMemberMessages = memberMessages;
    }

    @Exclude
    public boolean getModeratorMessages() {
        return mModeratorMessages;
    }

    @Exclude
    public void setModeratorMessages(boolean moderatorMessages) {
        mModeratorMessages = moderatorMessages;
    }

    @Exclude
    public boolean getQuizNotifs() {
        return mQuizNotifs;
    }

    @Exclude
    public void setQuizNotifs(boolean quizNotifs) {
        mQuizNotifs = quizNotifs;
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
