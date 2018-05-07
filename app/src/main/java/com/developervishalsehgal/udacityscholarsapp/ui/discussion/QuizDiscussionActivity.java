package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.Discussion;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class QuizDiscussionActivity extends AppCompatActivity implements QuizDiscussionContract.View {

    private QuizDiscussionContract.Presenter mPresenter;
    private QuizDiscussionAdapter mQuizDiscussionAdapter;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_discussion);

        // Injecting presenter reference
        PresenterInjector.injectQuizDiscussionPresenter(this);

        initializeUI();

        mPresenter.start(getIntent().getExtras());
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }

    private void initializeUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView mQuizRecyclerView = findViewById(R.id.recyclerview_messages);
        mQuizRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mQuizRecyclerView.setLayoutManager(linearLayoutManager);

        mQuizDiscussionAdapter = new QuizDiscussionAdapter(mFirebaseUser.getDisplayName());

        mQuizRecyclerView.setAdapter(mQuizDiscussionAdapter);
        EditText messageEditText = findViewById(R.id.message_edit_text);
        ImageButton sendMessageButton = findViewById(R.id.message_send_button);
        sendMessageButton.setOnClickListener(view -> sendMessage(messageEditText.getText().toString().trim()));
    }

    private void sendMessage(String message) {
        Comment comment = new Comment();
        comment.setCommentBy(mFirebaseUser.getDisplayName());
        comment.setCommenterId(mFirebaseUser.getUid());
        comment.setComment(message);
        comment.setCommentedOn(System.currentTimeMillis());
        Uri photoUri = mFirebaseUser.getPhotoUrl();
        if (photoUri != null) {
            comment.setImage(photoUri.toString());
        }
        mPresenter.onSendMessageClicked(comment);
        mQuizDiscussionAdapter.addNewMessage(comment);
    }

    @Override
    public void loadDiscussion(Discussion discussion) {
        mQuizDiscussionAdapter.loadCommentHistory(discussion.getComments());
    }

    @Override
    public void onDiscussionLoadError() {
        // TODO: show an alert or toast saying "discussion can't be loaded at the moment, check network connection and try again"
    }

    @Override
    public void onSendMessageSuccess() {
        // TODO: Do something if the message is sent successfully
    }

    @Override
    public void onSendMessageFailed() {
        // TODO: Do something if the message has failed to send
    }

    @Override
    public void showReceivedMessage(Comment comment) {
        mQuizDiscussionAdapter.addNewMessage(comment);
    }

    @Override
    public void setPresenter(QuizDiscussionContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        // TODO: Show progress bar / dialog here
    }

    @Override
    public void hideLoading() {
        // TODO: Hide progress bar / dialog here
    }
}
