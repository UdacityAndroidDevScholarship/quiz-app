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

    private static final String KEY_USER_ATTEMPTED_QUIZ = "attempted";
    private static final String KEY_DISCUSSION_COMMENTS = "comments";

    private static final String KEY_USER_BOOKMARKS = "bookmarks";
    //

    private DatabaseReference mUsersRef;
    private DatabaseReference mQuizzesRef;
    private DatabaseReference mDiscussionsRef;

    private List<ValueEventListener> mValueListeners;

    // Private variables
    private FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

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
                        try {
                            Quiz singleQuiz = childSnapshot.getValue(Quiz.class);
                            if (singleQuiz != null && singleQuiz.getTitle() != null) {
                                singleQuiz.setKey(childSnapshot.getKey());
                                quizList.add(singleQuiz);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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

        // TODO: Implement pagination here.
        if (limitToFirst > 0) {
            quizzesRefQuery.limitToFirst(limitToFirst);
        }
        quizzesRefQuery.addValueEventListener(listener);
        mValueListeners.add(listener);
    }

    @Override
    public void fetchAttemptedQuizzes(Callback<List<QuizAttempted>> callback) {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot != null) {
                    List<QuizAttempted> quizzesAttempted = new ArrayList<>();
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        try {
                            QuizAttempted singleQuizAttempted = childSnapshot.getValue(QuizAttempted.class);
                            if (singleQuizAttempted != null && singleQuizAttempted.getQuizTitle() != null) {
                                singleQuizAttempted.setKey(childSnapshot.getKey());
                                quizzesAttempted.add(singleQuizAttempted);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    callback.onReponse(quizzesAttempted);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError();
            }
        };

        if (mCurrentUser == null) {
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        }

        mUsersRef.child(mCurrentUser.getUid()).child(KEY_USER_ATTEMPTED_QUIZ)
                .addValueEventListener(listener);
        mValueListeners.add(listener);
    }

    @Override
    public void fetchQuizById(String quizId, Callback<Quiz> callback) {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot != null) {
                    Quiz singleQuiz = snapshot.getValue(Quiz.class);
                    singleQuiz.setKey(snapshot.getKey());
                    callback.onReponse(singleQuiz);
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError();
            }
        };

        mQuizzesRef.child(quizId).addValueEventListener(listener);
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
        if (mCurrentUser == null) {
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        }

        if (userIdentifier == null) {
            userIdentifier = mCurrentUser.getUid();
        }


        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot != null) {
                    User user = snapshot.getValue(User.class);
                    callback.onReponse(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError();
            }
        };

        mUsersRef.child(mCurrentUser.getUid()).addValueEventListener(listener);
        mValueListeners.add(listener);


    }

    @Override
    public void setUserInfo(User currentUser, final Callback<Void> callback) {

        // Here we are not using setValue directly as that will overwrite the entire object and
        // we want to save bookmarks and attempted quizzes. Hence calling updateChildren

        Map<String, Object> userData = new HashMap<>();
        userData.put(KEY_USER_EMAIL, currentUser.getEmail());
        userData.put(KEY_FCM_TOKEN, FirebaseInstanceId.getInstance().getToken());
        userData.put(KEY_USER_PIC, currentUser.getImage());
        userData.put(KEY_USER_NAME, currentUser.getName());
        userData.put(KEY_SLACK_HANDLE, currentUser.getSlackHandle());
        userData.put(KEY_USER_STATUS, currentUser.getStatus());
        userData.put(KEY_USER_TRACK, currentUser.getTrack());
        userData.put(KEY_NOTIF_PREFS, currentUser.getNotificationPrefs());

        if (mCurrentUser == null) {
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        }

        mUsersRef.child(mCurrentUser.getUid()).updateChildren(userData)
                .addOnSuccessListener(aVoid -> callback.onReponse(null))
                .addOnFailureListener(e -> callback.onError());
    }

    @Override
    public void postComment(String discussionId, String quizId, Comment comment,
                            Callback<Void> callback) {
        if (mCurrentUser == null) {
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        }
        comment.setCommenterId(mCurrentUser.getUid());
        mDiscussionsRef.child(discussionId).child(KEY_DISCUSSION_COMMENTS).push().setValue(comment)
                .addOnSuccessListener(aVoid -> {
                    callback.onReponse(null);
                })
                .addOnFailureListener(e -> {
                    callback.onError();
                });
    }

    @Override
    public void updateMyAttemptedQuizzes(QuizAttempted quizAttempt, Callback<Void> callback) {

        if (mCurrentUser == null) {
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        }

        mUsersRef.child(mCurrentUser.getUid())
                .child(KEY_USER_ATTEMPTED_QUIZ)
                .child(quizAttempt.getQuizId())
                .setValue(quizAttempt)
                .addOnSuccessListener(aVoid -> callback.onReponse(null))
                .addOnFailureListener(e -> callback.onError());

    }

    @Override
    public void updateQuizBookmarkStatus(String quizIdentifier, boolean isBookmarked, Callback<Void> callback) {
        if (mCurrentUser == null) {
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        }

        mUsersRef.child(mCurrentUser.getUid()).child(KEY_USER_BOOKMARKS).child(quizIdentifier)
                .setValue(isBookmarked)
                .addOnSuccessListener(aVoid -> callback.onReponse(null))
                .addOnFailureListener(e -> callback.onError());
    }

    @Override
    public void getMyBookmarks(Callback<List<String>> callback) {

        if (mCurrentUser == null) {
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        }

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot != null) {
                    List<String> bookmarks = new ArrayList<>();
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        try {
                            boolean isAdded = (boolean) childSnapshot.getValue();
                            if (isAdded) {
                                bookmarks.add(childSnapshot.getKey());
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                    callback.onReponse(bookmarks);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError();
            }
        };

        mUsersRef.child(mCurrentUser.getUid()).child(KEY_USER_BOOKMARKS)
                .addValueEventListener(listener);

    }

    @Override
    public void getMyPreferences(Callback<NotificationPrefs> callback) {

    }

    @Override
    public void updateMyPrefs(NotificationPrefs prefs, Callback<Void> callback) {

    }

    @Override
    public void updateMyFCMToken(String fcmToken) {
        if (mCurrentUser == null) {
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        }

        if (mCurrentUser != null) {
            mUsersRef.child(mCurrentUser.getUid()).child(KEY_FCM_TOKEN).setValue(fcmToken);
        }

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

            if (mCurrentUser == null) {
                mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
            }

            mUsersRef.child(mCurrentUser.getUid()).child(property).setValue(value)
                    .addOnCompleteListener(task -> callback.onReponse(null))
                    .addOnFailureListener(e -> callback.onError());
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError();
        }
    }
}
