package com.developervishalsehgal.udacityscholarsapp.data;

import android.content.Context;
import android.graphics.Bitmap;

import com.developervishalsehgal.udacityscholarsapp.application.AppClass;
import com.developervishalsehgal.udacityscholarsapp.data.local.DBHandler;
import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.data.models.QuizAttempted;
import com.developervishalsehgal.udacityscholarsapp.data.models.User;
import com.developervishalsehgal.udacityscholarsapp.data.remote.FirebaseHandler;
import com.developervishalsehgal.udacityscholarsapp.data.remote.FirebaseProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * This is the implementation of {@link DataHandler} interface. This class should either directly
 * deal with shared preferences of via delegate class
 * <p>
 * TODO change this description after implementation
 *
 * @Author kaushald
 */
class AppDataHandler implements DataHandler {

    private static AppDataHandler INSTANCE = null;

    private PrefsHelper mPreferences;
    private DBHandler mDBHandler;
    private FirebaseHandler mFirebaseHandler;

    private AppDataHandler() {
        Context context = AppClass.getAppContext();
        mPreferences = PrefsHelper.getInstance(context);
        mDBHandler = DBHandler.getInstance(context);
        mFirebaseHandler = FirebaseProvider.provide();
    }

    static AppDataHandler getInstance() {
        if (INSTANCE == null) {
            synchronized (AppDataHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppDataHandler();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void fetchQuizzes(int limitToFirst, final Callback<List<Quiz>> callback) {
        mFirebaseHandler.fetchQuizzes(limitToFirst, new FirebaseCallback<>(callback));
    }

    @Override
    public void updateSlackHandle(String slackHandle, Callback<Void> callback) {
        mFirebaseHandler.updateSlackHandle(slackHandle, new FirebaseCallback<>(callback));
    }

    @Override
    public void updateUserName(String userName, Callback<Void> callback) {
        mFirebaseHandler.updateUserName(userName, new FirebaseCallback<>(callback));
    }

    @Override
    public void updateProfilePic(String profilePicUrl, Callback<Void> callback) {
        mFirebaseHandler.updateProfilePic(profilePicUrl, new FirebaseCallback<>(callback));
    }

    @Override
    public void uploadProfilePic(String localPicturePath, Callback<String> callback) {

    }

    @Override
    public void uploadProfilePic(Bitmap picBitmap, Callback<String> callback) {

    }

    @Override
    public void postComment(String discussionId, String quizId, Comment comment, Callback<Void> callback) {
        mFirebaseHandler.postComment(discussionId, quizId, comment, new FirebaseCallback<>(callback));
    }

    @Override
    public void updateMyAttemptedQuizzes(QuizAttempted quizAttempt, Callback<Void> callback) {
        mFirebaseHandler.updateMyAttemptedQuizzes(quizAttempt, new FirebaseCallback<>(callback));
    }

    @Override
    public void addBookmark(String quizIdentifier, Callback<Void> callback) {
        mFirebaseHandler.addBookmark(quizIdentifier, new FirebaseCallback<>(callback));
    }

    @Override
    public void saveUserName(String userName) {
        mPreferences.setUserName(userName);
    }

    @Override
    public String getUserName() {
        return mPreferences.getUserName();
    }

    @Override
    public void saveUserEmail(String userEmail) {
        mPreferences.setUserEmail(userEmail);
    }

    @Override
    public String getUserEmail() {
        return mPreferences.getUserEmail();
    }

    @Override
    public void saveUserPic(String picUrl) {
        mPreferences.setUserPic(picUrl);
    }

    @Override
    public String getUserPic() {
        return mPreferences.getUserPic();
    }

    @Override
    public void saveUserTrack(String userTrack) {
        mPreferences.setUserTrack(userTrack);
    }

    @Override
    public String getUserTrack() {
        return mPreferences.getUserTrack();
    }

    @Override
    public void saveSlackHandle(String slackHandle) {
        mPreferences.setSlackHandle(slackHandle);
    }

    @Override
    public String getSlackHandle() {
        return mPreferences.getSlackHandle();
    }

    @Override
    public void saveStatus(String status) {
        mPreferences.setUserStatus(status);
    }

    @Override
    public String getStatus() {
        return mPreferences.getUserStatus();
    }

    @Override
    public void setUserInfo(Callback<Void> callback) {

        User currentUser = new User();
        currentUser.setImage(mPreferences.getUserPic());
        currentUser.setName(mPreferences.getUserName());
        currentUser.setEmail(mPreferences.getUserEmail());
        currentUser.setSlackHandle(mPreferences.getSlackHandle());
        currentUser.setStatus(mPreferences.getUserStatus());
        currentUser.setTrack(mPreferences.getUserTrack());

        mFirebaseHandler.setUserInfo(currentUser, new FirebaseCallback<>(callback));
    }

    /**
     * internal class for converting {@link FirebaseHandler} Callback to {@link DataHandler} Callback
     *
     * @param <T> type of response that is expected
     */
    class FirebaseCallback<T> implements FirebaseHandler.Callback<T> {
        DataHandler.Callback<T> callback;

        FirebaseCallback(DataHandler.Callback<T> callback) {
            this.callback = callback;
        }

        @Override
        public void onReponse(T result) {
            this.callback.onResponse(result);
        }

        @Override
        public void onError() {
            this.callback.onError();
        }
    }
}
