
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model class representing a scholar, will mostly be used to represent current scholar
 */
@IgnoreExtraProperties
public class User {

    @Expose
    @SerializedName("email")
    private String mEmail;

    @Expose
    @SerializedName("image")
    private String mImage;

    @Expose
    @SerializedName("moderator")
    private boolean mModerator;

    @Expose
    @SerializedName("name")
    private String mName;

    @Expose
    @SerializedName("slack-handle")
    private String mSlackHandle;

    @Expose
    @SerializedName("status")
    private String mStatus;

    @Expose
    @SerializedName("track")
    private String mTrack;

    @Expose
    @SerializedName("attempted")
    private List<QuizAttempted> mAttemptedList;

    @Expose
    @SerializedName("prefs")
    private NotificationPrefs mNotificationPrefs;

    @Expose
    @SerializedName("bookmarks")
    private List<String> mBookmarks;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public boolean getModerator() {
        return mModerator;
    }

    public void setModerator(boolean moderator) {
        mModerator = moderator;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSlackHandle() {
        return mSlackHandle;
    }

    public void setSlackHandle(String slackHandle) {
        mSlackHandle = slackHandle;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTrack() {
        return mTrack;
    }

    public void setTrack(String track) {
        mTrack = track;
    }

    public List<QuizAttempted> getAttemptedList() {
        return mAttemptedList;
    }

    public void setAttemptedList(List<QuizAttempted> attemptedList) {
        this.mAttemptedList = attemptedList;
    }

    public NotificationPrefs getNotificationPrefs() {
        return mNotificationPrefs;
    }

    public void setNotificationPrefs(NotificationPrefs notificationPrefs) {
        this.mNotificationPrefs = notificationPrefs;
    }

    public List<String> getBookmarks() {
        return mBookmarks;
    }

    public void setBookmarks(List<String> bookmarks) {
        this.mBookmarks = bookmarks;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }
}