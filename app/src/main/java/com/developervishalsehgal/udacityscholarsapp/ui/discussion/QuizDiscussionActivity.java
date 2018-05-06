package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.Discussion;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class QuizDiscussionActivity extends AppCompatActivity implements DiscussionContract.View,View.OnClickListener{

    private RecyclerView rvDiscussion;
    private Button btnSend;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DiscussionAdapter discussionAdapter;
    private EditText edtComment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_discussion);

        Toolbar toolbar = findViewById(R.id.toolbarrnav);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        rvDiscussion = findViewById(R.id.rv_discussion);
        rvDiscussion.setLayoutManager(new LinearLayoutManager(this));

        btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);

        edtComment = findViewById(R.id.edt_discussion);

    }

    @Override
    public void setPresenter(DiscussionContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSendMessageSuccess() {

    }

    @Override
    public void onSendMessageError(String message) {

    }

    @Override
    public void onMessageReceivedFromCommunity(String message) {

    }

    @Override
    public void loadDiscussionHistory(List<Discussion> lDiscussion) {

    }

    @Override
    public void onDiscussionLoadError() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:

                Comment comment = new Comment();
                comment.setCommentBy(user.getDisplayName());

                String message = edtComment.getText().toString();
                comment.setComment(message);

                if(discussionAdapter == null){
                    List<Comment> lComment = new ArrayList<>();
                    lComment.add(comment);
                    discussionAdapter = new DiscussionAdapter(lComment);
                    rvDiscussion.setAdapter(discussionAdapter);
                }else{
                    discussionAdapter.addMessage(comment);
                }

                edtComment.setText("");




        }
    }
}
