package com.developervishalsehgal.udacityscholarsapp.data;

import android.graphics.Bitmap;

import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.data.models.QuizAttempted;
import com.developervishalsehgal.udacityscholarsapp.data.models.User;

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

    private PrefsHelper mPreferences;

    @Override
    public void fetchQuizzes(int limitToFirst, Callback<List<Quiz>> callback) {

    }

    @Override
    public void updateSlackHandle(String slackHandle, Callback<Void> callback) {

    }

    @Override
    public void updateUserName(String userName, Callback<Void> callback) {

    }

    @Override
    public void updateProfilePic(String profielPicUrl, Callback<Void> callback) {

    }

    @Override
    public void uploadProfilePic(String localPicturePath, Callback<String> callback) {

    }

    @Override
    public void uploadProfilePic(Bitmap picBitmap, Callback<String> callback) {

    }

    @Override
    public void setUserInfo(String userIdentifier, User currentUser, Callback<Void> callback) {

    }

    @Override
    public void postComment(String discussionId, String quizId, Comment comment, Callback<Void> callback) {

    }

    @Override
    public void updateMyAttemptedQuizzes(QuizAttempted quizAttempt, Callback<Void> callback) {

    }

    @Override
    public void addBookmark(String quizIdentifier, Callback<Void> callback) {

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
    public List<Quiz> getQuizzes() {
        // should get it from firebase database
        return null; // some mock data here
    }
}
