package com.developervishalsehgal.udacityscholarsapp.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Singleton class for dealing with Shared Preferences and local storage. This class is package
 * private so all communications of this class happens via {@link DataHandler}
 */
class PrefsHelper {

    private static final String PREFERENCES_NAME = "quiz_app_prefs";

    private SharedPreferences mPrefs;

    private static PrefsHelper sInstance = null;

    private PrefsHelper(Context context) {
        mPrefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static PrefsHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (PrefsHelper.class) {
                if (sInstance == null) {
                    sInstance = new PrefsHelper(context);
                }
            }
        }
        return sInstance;
    }

    private static final String KEY_USER_NAME = "key_user_name";
    private String mUserName;

    private static final String KEY_USER_EMAIL = "key_user_email";
    private String mUserEmail;

    private static final String KEY_USER_PIC = "key_user_image_url";
    private String mImageUrl;

    private static final String KEY_SLACK_HANDLE = "key_slack_handle";
    private String mSlackHandle;

    private static final String KEY_USER_STATUS = "key_user_status";
    private String mUserStatus;

    private static final String KEY_USER_TRACK = "key_user_track";
    private String mUserTrack;

    public void setUserName(String userName) {
        this.mUserName = userName;
        mPrefs.edit().putString(KEY_USER_NAME, userName).apply();
    }

    public String getUserName() {
        if (mUserName == null) {
            mUserName = mPrefs.getString(KEY_USER_NAME, null);
        }
        return mUserName;
    }

    public void setUserEmail(String email) {
        this.mUserEmail = email;
        mPrefs.edit().putString(KEY_USER_EMAIL, email).apply();
    }

    public String getUserEmail() {
        if (mUserEmail == null) {
            mUserEmail = mPrefs.getString(KEY_USER_EMAIL, null);
        }
        return mUserEmail;
    }

    public void setUserPic(String picUrl) {
        this.mImageUrl = picUrl;
        mPrefs.edit().putString(KEY_USER_PIC, picUrl).apply();
    }

    public String getUserPic() {
        if (mImageUrl == null) {
            mImageUrl = mPrefs.getString(KEY_USER_PIC, null);
        }
        return mImageUrl;
    }

    public void setSlackHandle(String slackHandle) {
        this.mSlackHandle = slackHandle;
        mPrefs.edit().putString(KEY_SLACK_HANDLE, slackHandle).apply();
    }

    public String getSlackHandle() {
        if (mSlackHandle == null) {
            mSlackHandle = mPrefs.getString(KEY_SLACK_HANDLE, null);
        }
        return mSlackHandle;
    }

    public void setUserStatus(String userStatus) {
        this.mUserStatus = userStatus;
        mPrefs.edit().putString(KEY_USER_STATUS, userStatus).apply();
    }

    public String getUserStatus() {
        if (mUserStatus == null) {
            mUserStatus = mPrefs.getString(KEY_USER_STATUS, null);
        }
        return mUserStatus;
    }

    public void setUserTrack(String userTrack) {
        this.mUserTrack = userTrack;
        mPrefs.edit().putString(KEY_USER_TRACK, userTrack).apply();
    }

    public String getUserTrack() {
        if (mUserTrack == null) {
            mUserTrack = mPrefs.getString(KEY_USER_TRACK, null);
        }
        return mUserTrack;
    }

    public void destroy() {
        mPrefs.edit().clear().apply();
        mUserStatus = null;
        mImageUrl = null;
        mSlackHandle = null;
        mUserEmail = null;
        mUserTrack = null;
        mUserName = null;
    }

}
