
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model class representing a scholar, will mostly be used to represent current scholar
 */
public class User {

    @SerializedName("email")
    private String mEmail;

    @SerializedName("image")
    private String mImage;

    @SerializedName("moderator")
    private Boolean mModerator;

    @SerializedName("name")
    private String mName;

    @SerializedName("slack-handle")
    private String mSlackHandle;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("track")
    private String mTrack;

    @SerializedName("attempted")
    private List<QuizAttempted> mAttemptedList;

    @SerializedName("prefs")
    private NotificationPrefs mNotificationPrefs;

    @SerializedName("bookmarks")
    private List<String> mBookmarks;

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

    public Boolean getModerator() {
        return mModerator;
    }

    public void setModerator(Boolean moderator) {
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
}
