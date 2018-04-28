
package com.developervishalsehgal.udacityscholarsapp.data.models;

<<<<<<< HEAD
=======
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
>>>>>>> upstream/development
import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a User's notification preferences
 */
<<<<<<< HEAD
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

=======
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

>>>>>>> upstream/development
}
