package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Discussion;

import java.util.List;

public class QuizDiscussionActivity extends AppCompatActivity implements DiscussionContract.View{

    RecyclerView rvDiscussion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_discussion);

        rvDiscussion = findViewById(R.id.rv_discussion);

        rvDiscussion.setAdapter(new DiscussionAdapter(null));
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
}
