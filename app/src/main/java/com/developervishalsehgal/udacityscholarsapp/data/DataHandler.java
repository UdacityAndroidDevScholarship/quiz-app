package com.developervishalsehgal.udacityscholarsapp.data;

import android.graphics.Bitmap;

import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
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
 */

public interface DataHandler {

    /**
     * Saves current user's data on remote database. All the paramters are read from local store
     * so no need to pass any paramters.
     *
     * @param callback callback for status of the operation
     */
    void setUserInfo(Callback<Void> callback);

    /**
     * Fetches quizzes based on parameters passed
     *
     * @param limitToFirst how many quizzes to be fetched? pass 0 to fetch all
     * @param callback     Callback for receiving result
     */
    void fetchQuizzes(int limitToFirst, Callback<List<Quiz>> callback);

    /**
     * Fetches quizzes based on parameters passed
     *
     * @param quizId   Id of the quiz to be fetched
     * @param callback Callback for receiving result
     */
    void fetchQuizById(String quizId, Callback<Quiz> callback);

    /**
     * Fetches quizzes already attempted by current user
     *
     * @param callback Callback for receiving result
     */
    void fetchAttemptedQuizzes(Callback<List<QuizAttempted>> callback);

    /**
     * Updates Slack handle in user's profile
     *
     * @param slackHandle new slack handle
     * @param callback    callback for status of operation
     */
    void updateSlackHandle(String slackHandle, Callback<Void> callback);

    /**
     * Updates user's FCM token
     *
     * @param fcmToken new FCM token
     */
    void updateFCMToken(String fcmToken);

    /**
     * Updates user's name in user's profile
     *
     * @param userName new name of the user (something like <b>The master of disaster<b/>)
     * @param callback callback for status of operation
     */
    void updateUserName(String userName, Callback<Void> callback);

    /**
     * Updates user's pic in user's profile
     *
     * @param profilePicUrl new profile pic open URL
     * @param callback      callback for status of operation
     */
    void updateProfilePic(String profilePicUrl, Callback<Void> callback);

    /**
     * Updates user's pic in user's profile
     *
     * @param localPicturePath local path of the image to be uploaded as profile pic
     * @param callback         callback for receiving open URL of the uploaded pic
     */
    @Deprecated
    void uploadProfilePic(String localPicturePath, Callback<String> callback);

    /**
     * Updates user's pic in user's profile
     *
     * @param picBitmap {@link Bitmap} representing new profile pic
     * @param callback  callback for receiving open URL of the uploaded pic
     */
    void uploadProfilePic(Bitmap picBitmap, Callback<String> callback);

    /**
     * Posts a comment in a discussion of a quiz
     *
     * @param discussionId id of discussion
     * @param quizId       id of the quiz this discussion belongs to
     * @param comment      scholar's comment
     * @param callback     callback for status of operation
     */
    void postComment(String discussionId, String quizId, String comment, Callback<Void> callback);

    /**
     * Updates user's attempted quizzes in remote database
     *
     * @param quizAttempt {@link QuizAttempted} object representing the attempt to be uploaded
     * @param callback    callback for status of operation
     */
    void updateMyAttemptedQuizzes(QuizAttempted quizAttempt, Callback<Void> callback);

    /**
     * Adds a quiz in user's bookmarks. Or favourites a quiz
     *
     * @param quizIdentifier id of the quiz to be bookmarked
     * @param callback       callback for status of operation
     */
    void updateQuizBookmarkStatus(String quizIdentifier, boolean isBookmarked, Callback<Void> callback);

    /**
     * Gets user's bookmarked quizzes
     *
     * @param callback callback for getting list of user bookmarks
     */
    void getMyBookmarks(Callback<List<String>> callback);

    /**
     * Saves user name locally
     *
     * @param userName name of the current user
     */
    void saveUserName(String userName);

    /**
     * Gets name of user from local store
     *
     * @return name of the user if present, null otherwise
     */
    String getUserName();

    /**
     * Saves user email locally
     *
     * @param userEmail email of the current user
     */
    void saveUserEmail(String userEmail);

    /**
     * Gets email of user from local store
     *
     * @return email of the user if present, null otherwise
     */
    String getUserEmail();

    /**
     * Saves user profile picture URL locally
     *
     * @param picUrl URL of the current user's profile pic
     */
    void saveUserPic(String picUrl);

    /**
     * Gets profile pic URL of user from local store
     *
     * @return profile pic url of the user if present, null otherwise
     */
    String getUserPic();

    /**
     * Saves user track locally
     *
     * @param userTrack current user's track
     */
    void saveUserTrack(String userTrack);

    /**
     * Gets track of user from local store
     *
     * @return Track of the user if present, null otherwise
     */
    String getUserTrack();

    /**
     * Saves user slack handle locally
     *
     * @param slackHandle user's slack handle
     */
    void saveSlackHandle(String slackHandle);

    /**
     * Gets slack handle of user from local store
     *
     * @return Slack handle of the user if present, null otherwise
     */
    String getSlackHandle();

    /**
     * Saves current user status locally
     *
     * @param status user's status
     */
    void saveStatus(String status);

    /**
     * Gets status of user from local store
     *
     * @return status of current user if present, null otherwise
     */
    String getStatus();

    void addNotification(Notification notification);

    List<Notification> getAllNotifications(int startFrom, int limit);

    List<Notification> searchNotifications(String query, int startFrom, int limit);

    /**
     * Generic callback interface for passing response to caller.
     * <p>
     * TODO: Replace all such callbacks with reactive programing, just pass observables
     *
     * @param <T> Type of expected response
     */
    interface Callback<T> {
        /**
         * Gets invoked when call was successful
         *
         * @param result result of the operation
         */
        void onResponse(T result);

        /**
         * Gets invoked when there is an error in the operation
         */
        void onError();
    }
}
