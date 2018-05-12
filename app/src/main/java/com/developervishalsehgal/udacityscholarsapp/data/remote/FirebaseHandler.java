package com.developervishalsehgal.udacityscholarsapp.data.remote;

import android.graphics.Bitmap;

import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.NotificationPrefs;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.data.models.QuizAttempted;
import com.developervishalsehgal.udacityscholarsapp.data.models.Resource;
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
    String REF_RESOURCES_NODE = "resources";

    /**
     * Fetches quizzes based on parameters passed
     *
     * @param limitToFirst how many quizzes to be fetched? pass 0 to fetch all
     */
    void fetchQuizzes(int limitToFirst, Callback<List<Quiz>> callback);

    void fetchAttemptedQuizzes(Callback<List<QuizAttempted>> callback);

    void fetchQuizById(String quizId, Callback<Quiz> callback);

    void updateSlackHandle(String slackHandle, Callback<Void> callback);

    void updateUserName(String userName, Callback<Void> callback);

    void updateProfilePic(String profielPicUrl, Callback<Void> callback);

    void uploadProfilePic(String localPicturePath, Callback<String> callback);

    void uploadProfilePic(Bitmap picBitmap, Callback<String> callback);

    void fetchUserInfo(String userIdentifier, Callback<User> callback);

    void fetchUserScore(String quizId, Callback<Integer> callback);

    void setUserInfo(User currentUser, Callback<Void> callback);

    void postComment(String discussionId, String quizId, Comment comment, Callback<Void> callback);

    void getComments(String discussionId, String quizId, Callback<List<Comment>> callback);

    void updateMyAttemptedQuizzes(QuizAttempted quizAttempt, Callback<Void> callback);

    void updateQuizBookmarkStatus(String quizIdentifier, boolean isBookmarked, Callback<Void> callback);

    void getMyBookmarks(Callback<List<String>> callback);

    void getMyPreferences(Callback<NotificationPrefs> callback);

    void updateMyPrefs(NotificationPrefs prefs, Callback<Void> callback);

    void updateMyFCMToken(String fcmToken);

    void updateMyStatus(String newStatus, Callback<Void> callback);

    void fetchResources(int startFrom, int limit, Callback<List<Resource>> callback);

    void destroy();

    /**
     * Generic callback interface for passing response to caller.
     * <p>
     * TODO Replace all such callbacks with reactive programing, just pass observables
     *
     * @param <T> Type of expected response
     */
    interface Callback<T> {
        void onReponse(T result);

        void onError();
    }

}