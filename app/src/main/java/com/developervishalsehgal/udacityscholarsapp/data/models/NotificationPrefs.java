
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a User's notification preferences
 */
public class NotificationPrefs {

    @SerializedName("comment-notifs")
    private Boolean mCommentNotifs;

    @SerializedName("member-messages")
    private Boolean mMemberMessages;

    @SerializedName("moderator-messages")
    private Boolean mModeratorMessages;

    @SerializedName("quiz-notifs")
    private Boolean mQuizNotifs;

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

}
