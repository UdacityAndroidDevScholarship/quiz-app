package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.ui.home.HomeContract;

public class QuizDiscussionPresenter implements QuizDiscussionContract.Presenter {

    private QuizDiscussionContract.View mView;
    private DataHandler mDataHandler;

    public QuizDiscussionPresenter(QuizDiscussionContract.View view) {
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();

        this.mView.setPresenter(this);
    }

    @Override
    public void start(@Nullable Bundle extras) {
        //TODO load comments from model
    }

    @Override
    public void destroy() {
        this.mView = null;
        this.mDataHandler = null;
    }

    @Override
    public void onClickedSendComment(String comment) {

        //TODO actual implementation to be done
        mView.loadComment(null);
    }
}
