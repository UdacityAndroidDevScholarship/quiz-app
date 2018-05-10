package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.Discussion;

public class QuizDiscussionPresenter implements QuizDiscussionContract.Presenter {

    private DataHandler mDataHandler;
    private QuizDiscussionContract.View mView;

    public QuizDiscussionPresenter(QuizDiscussionContract.View view) {
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();

        // This should be the last statement
        this.mView.setPresenter(this);
    }

    @Override
    public void start(@Nullable Bundle extras) {
        mView.showLoading();
        mDataHandler.fetchDiscussions(0, new DataHandler.Callback<Discussion>() {
            @Override
            public void onResponse(Discussion discussion) {
                mView.loadDiscussion(discussion);
                mView.hideLoading();
            }

            @Override
            public void onError() {
                mView.onDiscussionLoadError();
                mView.hideLoading();
            }
        });
    }

    @Override
    public void destroy() {
        this.mView = null;
        this.mDataHandler = null;
    }

    @Override
    public void onSendMessageClicked(Comment comment) {
        mView.showLoading();
        mDataHandler.sendQuizDiscussionMessage(comment, new DataHandler.Callback<Void>() {
            @Override
            public void onResponse(Void result) {
                mView.hideLoading();
                mView.onSendMessageSuccess();
            }

            @Override
            public void onError() {
                mView.hideLoading();
                mView.onSendMessageFailed();
            }
        });
    }

    @Override
    public void onMessageReceived(Comment comment) {
        mView.showReceivedMessage(comment);
    }
}
