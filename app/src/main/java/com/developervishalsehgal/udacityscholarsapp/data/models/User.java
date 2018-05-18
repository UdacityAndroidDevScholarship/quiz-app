
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Objects;

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
    @SerializedName("fcm-token")
    private String mFcmToken;

    @Expose
    @SerializedName("attempted")
    private Map<String, QuizAttempted> mAttemptedList;

    @Expose
    @SerializedName("prefs")
    private NotificationPrefs mNotificationPrefs;

    @Expose
    @SerializedName("bookmarks")
    private Map<String, Boolean> mBookmarks;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

    @PropertyName("email")
    public String getEmail() {
        return mEmail;
    }

    @PropertyName("email")
    public void setEmail(String email) {
        mEmail = email;
    }

    @PropertyName("image")
    public String getImage() {
        return mImage;
    }

    @PropertyName("image")
    public void setImage(String image) {
        mImage = image;
    }

    @PropertyName("moderator")
    public boolean getModerator() {
        return mModerator;
    }

    @PropertyName("moderator")
    public void setModerator(boolean moderator) {
        mModerator = moderator;
    }

    @PropertyName("name")
    public String getName() {
        return mName;
    }

    @PropertyName("name")
    public void setName(String name) {
        mName = name;
    }

    @PropertyName("slack-handle")
    public String getSlackHandle() {
        return mSlackHandle;
    }

    @PropertyName("slack-handle")
    public void setSlackHandle(String slackHandle) {
        mSlackHandle = slackHandle;
    }

    @PropertyName("status")
    public String getStatus() {
        return mStatus;
    }

    @PropertyName("status")
    public void setStatus(String status) {
        mStatus = status;
    }

    @PropertyName("track")
    public String getTrack() {
        return mTrack;
    }

    @PropertyName("track")
    public void setTrack(String track) {
        mTrack = track;
    }

    @PropertyName("fcm-token")
    public String getFcmToken() {
        return mFcmToken;
    }

    @PropertyName("fcm-token")
    public void setFcmToken(String fcmToken) {
        this.mFcmToken = fcmToken;
    }

    @PropertyName("attempted")
    public Map<String, QuizAttempted> getAttemptedList() {
        return mAttemptedList;
    }

    @PropertyName("attempted")
    public void setAttemptedList(Map<String, QuizAttempted> attemptedList) {
        this.mAttemptedList = attemptedList;
    }

    @PropertyName("prefs")
    public NotificationPrefs getNotificationPrefs() {
        return mNotificationPrefs;
    }

    @PropertyName("prefs")
    public void setNotificationPrefs(NotificationPrefs notificationPrefs) {
        this.mNotificationPrefs = notificationPrefs;
    }

    @PropertyName("bookmarks")
    public Map<String, Boolean> getBookmarks() {
        return mBookmarks;
    }

    @PropertyName("bookmarks")
    public void setBookmarks(Map<String, Boolean> bookmarks) {
        this.mBookmarks = bookmarks;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        this.mKey = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return mModerator == user.mModerator &&
                Objects.equals(mEmail, user.mEmail) &&
                Objects.equals(mImage, user.mImage) &&
                Objects.equals(mName, user.mName) &&
                Objects.equals(mSlackHandle, user.mSlackHandle) &&
                Objects.equals(mTrack, user.mTrack);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mEmail, mImage, mModerator, mName, mSlackHandle, mTrack);
    }
}
