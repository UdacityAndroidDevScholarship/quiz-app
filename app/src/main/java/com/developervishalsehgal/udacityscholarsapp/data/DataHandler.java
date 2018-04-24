package com.developervishalsehgal.udacityscholarsapp.data;

import android.graphics.Bitmap;

import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.data.models.QuizAttempted;
import com.developervishalsehgal.udacityscholarsapp.data.models.User;

import java.util.List;

/**
 * Data layer abstraction. All data related communication should happen via this interface. This
 * is the only point of interaction with shared preferences, local sqlite database, firebase and
 * network
 *
 * @author kaushald
 *
 */

public interface DataHandler {

    void fetchQuizzes(int limitToFirst, Callback<List<Quiz>> callback);

    void updateSlackHandle(String slackHandle, Callback<Void> callback);

    void updateUserName(String userName, Callback<Void> callback);

    void updateProfilePic(String profilePicUrl, Callback<Void> callback);

    void uploadProfilePic(String localPicturePath, Callback<String> callback);

    void uploadProfilePic(Bitmap picBitmap, Callback<String> callback);

    void setUserInfo(String userIdentifier, User currentUser, Callback<Void> callback);

    void postComment(String discussionId, String quizId, Comment comment, Callback<Void> callback);

    void updateMyAttemptedQuizzes(QuizAttempted quizAttempt, Callback<Void> callback);

    void addBookmark(String quizIdentifier, Callback<Void> callback);

    void saveUserName(String userName);

    String getUserName();

    void saveUserEmail(String userEmail);

    String getUserEmail();

    void saveUserPic(String picUrl);

    String getUserPic();

    void saveUserTrack(String userTrack);

    String getUserTrack();

    List<Quiz> getQuizzes();

    /**
     * Generic callback interface for passing response to caller.
     *
     * TODO Replace all such callbacks with reactive programing, just pass observables
     *
     * @param <T> Type of expected response
     */
    interface Callback<T> {
        void onResponse(T result);
        void onError();
    }
}
