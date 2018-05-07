package com.developervishalsehgal.udacityscholarsapp.data;

import android.content.Context;
import android.graphics.Bitmap;

import com.developervishalsehgal.udacityscholarsapp.application.AppClass;
import com.developervishalsehgal.udacityscholarsapp.data.local.DBHandler;
import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.data.models.QuizAttempted;
import com.developervishalsehgal.udacityscholarsapp.data.models.User;
import com.developervishalsehgal.udacityscholarsapp.data.remote.FirebaseHandler;
import com.developervishalsehgal.udacityscholarsapp.data.remote.FirebaseProvider;

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
        // Fetch all the quizzes
        mFirebaseHandler.fetchQuizzes(limitToFirst, new FirebaseCallback<List<Quiz>>(callback) {
            @Override
            public void onReponse(List<Quiz> quizzes) {

                // Fetch quizzes attempted by user
                mFirebaseHandler.fetchAttemptedQuizzes(new FirebaseHandler.Callback<List<QuizAttempted>>() {
                    @Override
                    public void onReponse(List<QuizAttempted> attempts) {

                        // Mark attempted quizzes
                        for (Quiz singleQuiz : quizzes) {
                            for (QuizAttempted attempt : attempts) {
                                if (singleQuiz.getKey().equalsIgnoreCase(attempt.getQuizId())) {
                                    singleQuiz.setAttempted(true);
                                    break;
                                }
                            }
                        }

                        // return the marked response
                        callback.onResponse(quizzes);
                    }

                    @Override
                    public void onError() {
                        callback.onError();
                    }
                });
            }
        });
    }

    @Override
    public void fetchAttemptedQuizzes(Callback<List<QuizAttempted>> callback) {
        mFirebaseHandler.fetchAttemptedQuizzes(new FirebaseCallback<>(callback));
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
        // TODO: Implement this feature using firebase storage
        throw new RuntimeException("Feature not implemented");
    }

    @Override
    public void uploadProfilePic(Bitmap picBitmap, Callback<String> callback) {
        // TODO: Implement this feature using firebase storage
        throw new RuntimeException("Feature not implemented");
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

    @Override
    public void addNotification(Notification notification) {
        mDBHandler.addNotification(notification);
    }

    @Override
    public List<Notification> getAllNotifications(int startFrom, int limit) {
        return mDBHandler.getAllNotification(startFrom, limit);
    }

    @Override
    public List<Notification> searchNotifications(String query, int startFrom, int limit) {
        return mDBHandler.searchNotifications(query, startFrom, limit);
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
