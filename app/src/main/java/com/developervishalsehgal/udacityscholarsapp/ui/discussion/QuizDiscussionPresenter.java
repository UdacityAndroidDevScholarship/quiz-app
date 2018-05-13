package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;

import java.util.List;

public class QuizDiscussionPresenter implements QuizDiscussionContract.Presenter {

    private QuizDiscussionContract.View mView;
    private DataHandler mDataHandler;

    private String quizId;

    public QuizDiscussionPresenter(QuizDiscussionContract.View view) {
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();

        this.mView.setPresenter(this);
    }

    @Override
    public void start(@Nullable Bundle extras) {
        if (extras == null || !extras.containsKey(QuizDiscussionContract.KEY_QUIZ_ID)) {
            mView.showInvalidInput();
            return;
        }

        quizId = extras.getString(QuizDiscussionContract.KEY_QUIZ_ID);

        mDataHandler.fetchComments(quizId, quizId, new DataHandler.Callback<List<Comment>>() {
            @Override
            public void onResponse(List<Comment> result) {
                mView.loadComments(result);
            }

            @Override
            public void onError() {
                mView.onCommentsLoadError();
            }
        });
    }

    @Override
    public void destroy() {
        this.mView = null;
        this.mDataHandler = null;
    }

    @Override
    public void onClickedSendComment(String comment) {
        mDataHandler.postComment(quizId, quizId, comment, new DataHandler.Callback<Void>() {
            @Override
            public void onResponse(Void result) {
                // DO nothing
            }

            @Override
            public void onError() {

            }
        });
    }
}
