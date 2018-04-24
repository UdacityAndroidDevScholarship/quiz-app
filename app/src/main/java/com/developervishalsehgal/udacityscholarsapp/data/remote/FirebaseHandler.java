package com.developervishalsehgal.udacityscholarsapp.data.remote;

import android.graphics.Bitmap;

import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.NotificationPrefs;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.data.models.QuizAttempted;
import com.developervishalsehgal.udacityscholarsapp.data.models.User;

import java.util.List;

/**
 * The only point of interaction with firebase remote database. This contract is kept separate from
 * implementation for loose coupling
 * <p>
 * TODO change description after implementation
 *
 * @Author kaushald
 */
public interface FirebaseHandler {

    String REF_USERS_NODE = "users";
    String REF_QUIZZES_NODE = "quizzes";
    String REF_DISCUSSION_NODE = "discussions";

    /**
     * Fetches quizzes based on parameters passed
     *
     * @param limitToFirst how many quizzes to be fetched? pass 0 to fetch all
     */
    void fetchQuizzes(int limitToFirst, Callback<List<Quiz>> callback);

    void updateSlackHandle(String myId, String slackHandle, Callback<Void> callback);

    void updateUserName(String myId, String userName, Callback<Void> callback);

    void updateProfilePic(String myId, String profielPicUrl, Callback<Void> callback);

    void uploadProfilePic(String myId, String localPicturePath, Callback<String> callback);

    void uploadProfilePic(String myId, Bitmap picBitmap, Callback<String> callback);

    void fetchUserInfo(String userIdentifier, Callback<User> callback);

    void setUserInfo(String userIdentifier, User currentUser, Callback<Void> callback);

    void postComment(String discussionId, String quizId, Comment comment, Callback<Void> callback);

    void updateMyAttemptedQuizzes(String myId, QuizAttempted quizAttempt, Callback<Void> callback);

    void addBookmark(String myId, String quizIdentifier, Callback<Void> callback);

    void getMyBookmarks(String myId, Callback<String> callback);

    void getMyPreferences(String myId, Callback<NotificationPrefs> callback);

    void updateMyPrefs(String myId, NotificationPrefs prefs, Callback<Void> callback);

    void updateMyFCMToken(String myId, String fcmToken, Callback<Void> callback);

    void updateMyStatus(String myId, String newStatus, Callback<Void> callback);

    void destroy();

    /**
     * Generic callback interface for passing response to caller.
     *
     * TODO Replace all such callbacks with reactive programing, just pass observables
     *
     * @param <T> Type of expected response
     */
    interface Callback<T> {
        void onReponse(T result);

        void onError();
    }

}
