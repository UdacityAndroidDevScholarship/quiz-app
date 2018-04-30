package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

public class HomePresenter implements HomeContract.Presenter{

    private DataHandler mDataHandler;
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view){
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();

        // This should be the last statement
        this.mView.setPresenter(this);
    }


    @Override
    public void start(@Nullable Bundle extras) {
        // TODO: check the bundle extras here if it contains quiz, or resource or some other id.
        // TODO: If it does, take action accordingly and navigate to the right quiz / resource.
    }

    @Override
    public void destroy() {
        this.mView = null;
        this.mDataHandler = null;
    }

    @Override
    public void onQuizClicked(Quiz quiz) {
        mView.navigateToQuizDesc(quiz);
    }

    @Override
    public void onLogoutClicked() {

    }

    @Override
    public void onAllQuizSelected() {

    }

    @Override
    public void onAttemptedQuizSelected() {

    }

    @Override
    public void onUnAttemptedQuizSelected() {

    }

    @Override
    public void onBookmarkQuizSelected() {

    }
}
