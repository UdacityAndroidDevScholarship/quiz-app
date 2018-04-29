package com.developervishalsehgal.udacityscholarsapp.data.remote;

import android.graphics.Bitmap;

import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.NotificationPrefs;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.data.models.QuizAttempted;
import com.developervishalsehgal.udacityscholarsapp.data.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All the firebase related stuff in this class and its parent directory. Firebase dependencies
 * should not propagate outside of this package.
 * <p>
 * TODO change description after implementation
 *
 * @Author kaushald
 */
class FirebaseHandlerImpl implements FirebaseHandler {

    // KEYS here
    private static final String KEY_SLACK_HANDLE = "slack-handle";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_PIC = "image";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_FCM_TOKEN = "fcm-token";
    private static final String KEY_USER_STATUS = "status";
    private static final String KEY_USER_TRACK = "track";
    private static final String KEY_NOTIF_PREFS = "prefs";
    //

    private DatabaseReference mUsersRef;
    private DatabaseReference mQuizzesRef;
    private DatabaseReference mDiscussionsRef;

    private List<ValueEventListener> mValueListeners;

    // Private variables

    FirebaseHandlerImpl() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = firebaseDatabase.getReference();

        mValueListeners = new ArrayList<>();

        mUsersRef = rootRef.child(REF_USERS_NODE);
        mQuizzesRef = rootRef.child(REF_QUIZZES_NODE);
        mDiscussionsRef = rootRef.child(REF_DISCUSSION_NODE);
    }


    @Override
    public void fetchQuizzes(int limitToFirst, final Callback<List<Quiz>> callback) {

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot != null) {
                    List<Quiz> quizList = new ArrayList<>();
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        Quiz singleQuiz = childSnapshot.getValue(Quiz.class);
                        if (singleQuiz != null) {
                            singleQuiz.setKey(childSnapshot.getKey());
                            quizList.add(singleQuiz);
                        }
                    }
                    callback.onReponse(quizList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError();
            }
        };

        Query quizzesRefQuery = mQuizzesRef;

        // TODO Implement pagination here.
        if (limitToFirst > 0) {
            quizzesRefQuery.limitToFirst(limitToFirst);
        }
        quizzesRefQuery.addValueEventListener(listener);
        mValueListeners.add(listener);
    }

    @Override
    public void updateSlackHandle(String slackHandle, final Callback<Void> callback) {
        updateUserProperty(KEY_SLACK_HANDLE, slackHandle, callback);
    }

    @Override
    public void updateUserName(String userName, final Callback<Void> callback) {
        updateUserProperty(KEY_USER_NAME, userName, callback);
    }

    @Override
    public void updateProfilePic(String profilePicUrl, final Callback<Void> callback) {
        updateUserProperty(KEY_USER_PIC, profilePicUrl, callback);
    }

    @Override
    public void uploadProfilePic(String localPicturePath, Callback<String> callback) {
        // TODO user firebase storage here to upload the user profile pic and send the
        // (TODO contd.) public URL in callback response
    }

    @Override
    public void uploadProfilePic(Bitmap picBitmap, Callback<String> callback) {
        // TODO user firebase storage here to upload the user profile pic and send the
        // (TODO contd.) public URL in callback response
    }

    @Override
    public void fetchUserInfo(String userIdentifier, Callback<User> callback) {

    }

    @Override
    public void setUserInfo(User currentUser, final Callback<Void> callback) {

        // Here we are not using setValue directly as that will overwrite the entire object and
        // we want to save bookmarks and attempted quizzes. Hence calling updateChildren
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userIdentifier = firebaseUser.getUid();

        Map<String, Object> userData = new HashMap<>();
        userData.put(KEY_USER_EMAIL, currentUser.getEmail());
        userData.put(KEY_FCM_TOKEN, FirebaseInstanceId.getInstance().getToken());
        userData.put(KEY_USER_PIC, currentUser.getImage());
        userData.put(KEY_USER_NAME, currentUser.getName());
        userData.put(KEY_SLACK_HANDLE, currentUser.getSlackHandle());
        userData.put(KEY_USER_STATUS, currentUser.getStatus());
        userData.put(KEY_USER_TRACK, currentUser.getTrack());
        userData.put(KEY_NOTIF_PREFS, currentUser.getNotificationPrefs());

        mUsersRef.child(userIdentifier).updateChildren(userData)
                .addOnSuccessListener(aVoid -> callback.onReponse(null))
                .addOnFailureListener(e -> callback.onError());
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
    public void getMyBookmarks(Callback<String> callback) {

    }

    @Override
    public void getMyPreferences(Callback<NotificationPrefs> callback) {

    }

    @Override
    public void updateMyPrefs(NotificationPrefs prefs, Callback<Void> callback) {

    }

    @Override
    public void updateMyFCMToken(String fcmToken, Callback<Void> callback) {

    }

    @Override
    public void updateMyStatus(String newStatus, Callback<Void> callback) {

    }

    @Override
    public void destroy() {
        // Remove all listeners
        for (ValueEventListener listener : mValueListeners) {
            mQuizzesRef.removeEventListener(listener);
            mDiscussionsRef.removeEventListener(listener);
            mUsersRef.removeEventListener(listener);
        }
    }

    private void updateUserProperty(String property, String value, final Callback<Void> callback) {

        try {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String currentUserId = firebaseUser.getUid();

            mUsersRef.child(currentUserId).child(property).setValue(value)
                    .addOnCompleteListener(task -> callback.onReponse(null))
                    .addOnFailureListener(e -> callback.onError());
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError();
        }
    }
}
