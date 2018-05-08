
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Model class representing a scholar, will mostly be used to represent current scholar
 */
@IgnoreExtraProperties
public class User {

    @Expose
    @PropertyName("email")
    @SerializedName("email")
    String mEmail;

    @Expose
    @PropertyName("image")
    @SerializedName("image")
    String mImage;

    @Expose
    @PropertyName("moderator")
    @SerializedName("moderator")
    boolean mModerator;

    @Expose
    @PropertyName("name")
    @SerializedName("name")
    String mName;

    @Expose
    @PropertyName("slack-handle")
    @SerializedName("slack-handle")
    String mSlackHandle;

    @Expose
    @PropertyName("status")
    @SerializedName("status")
    String mStatus;

    @Expose
    @PropertyName("track")
    @SerializedName("track")
    String mTrack;

    @Expose
    @PropertyName("fcm-token")
    @SerializedName("fcm-token")
    String mFcmToken;

    @Expose
    @PropertyName("attempted")
    @SerializedName("attempted")
    Map<String, QuizAttempted> mAttemptedList;

    @Expose
    @PropertyName("prefs")
    @SerializedName("prefs")
    NotificationPrefs mNotificationPrefs;

    @Expose
    @PropertyName("bookmarks")
    @SerializedName("bookmarks")
    Map<String, Boolean> mBookmarks;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    String mKey;

    @Exclude
    public String getEmail() {
        return mEmail;
    }

    @Exclude
    public void setEmail(String email) {
        mEmail = email;
    }

    @Exclude
    public String getImage() {
        return mImage;
    }

    @Exclude
    public void setImage(String image) {
        mImage = image;
    }

    @Exclude
    public boolean getModerator() {
        return mModerator;
    }

    @Exclude
    public void setModerator(boolean moderator) {
        mModerator = moderator;
    }

    @Exclude
    public String getName() {
        return mName;
    }

    @Exclude
    public void setName(String name) {
        mName = name;
    }

    @Exclude
    public String getSlackHandle() {
        return mSlackHandle;
    }

    @Exclude
    public void setSlackHandle(String slackHandle) {
        mSlackHandle = slackHandle;
    }

    @Exclude
    public String getStatus() {
        return mStatus;
    }

    @Exclude
    public void setStatus(String status) {
        mStatus = status;
    }

    @Exclude
    public String getTrack() {
        return mTrack;
    }

    @Exclude
    public void setTrack(String track) {
        mTrack = track;
    }

    @Exclude
    public String getFcmToken() {
        return mFcmToken;
    }

    @Exclude
    public void setFcmToken(String fcmToken) {
        this.mFcmToken = fcmToken;
    }

    @Exclude
    public Map<String, QuizAttempted> getAttemptedList() {
        return mAttemptedList;
    }

    @Exclude
    public void setAttemptedList(Map<String, QuizAttempted> attemptedList) {
        this.mAttemptedList = attemptedList;
    }

    @Exclude
    public NotificationPrefs getNotificationPrefs() {
        return mNotificationPrefs;
    }

    @Exclude
    public void setNotificationPrefs(NotificationPrefs notificationPrefs) {
        this.mNotificationPrefs = notificationPrefs;
    }

    @Exclude
    public Map<String, Boolean> getBookmarks() {
        return mBookmarks;
    }

    @Exclude
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
