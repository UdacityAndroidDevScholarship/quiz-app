package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private DataHandler mDataHandler;
    private HomeContract.View mView;

    private List<Quiz> mQuizzes;

    public HomePresenter(HomeContract.View view) {
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();

        mQuizzes = new ArrayList<>();

        // This should be the last statement
        this.mView.setPresenter(this);
    }


    @Override
    public void start(@Nullable Bundle extras) {
        // TODO: check the bundle extras here if it contains quiz, or resource or some other id.
        // TODO: If it does, take action accordingly and navigate to the right quiz / resource.

        mView.showLoading();
        mDataHandler.fetchQuizzes(0, new DataHandler.Callback<List<Quiz>>() {
            @Override
            public void onResponse(List<Quiz> result) {
                mQuizzes.clear();
                mQuizzes.addAll(result);
                mView.loadQuizzes(result);
                mView.hideLoading();
            }

            @Override
            public void onError() {
                mView.onQuizLoadError();
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
    public void onQuizClicked(Quiz quiz) {
        mView.navigateToQuizDesc(quiz);
    }

    @Override
    public void onLogoutClicked() {
        // TODO: Clear all local storage. SQLite, Shared Preferences, perform firebase logout and
        // navigate to login screen again
    }

    @Override
    public void onAllQuizSelected() {
        // Refetch the list from server when all quizzes selected
        start(null);
    }

    @Override
    public void onAttemptedQuizSelected() {
        List<Quiz> attemptedQuizzes = new ArrayList<>();
        for (Quiz quiz : mQuizzes) {
            if (quiz.isAttempted()) {
                attemptedQuizzes.add(quiz);
            }
        }
        mView.loadQuizzes(attemptedQuizzes);
    }

    @Override
    public void onUnAttemptedQuizSelected() {
        List<Quiz> unAttemptedQuizzes = new ArrayList<>();
        for (Quiz quiz : mQuizzes) {
            if (!quiz.isAttempted()) {
                unAttemptedQuizzes.add(quiz);
            }
        }
        mView.loadQuizzes(unAttemptedQuizzes);
    }

    @Override
    public void onBookmarkSelected() {
        List<Quiz> bookmarkedQuizzes = new ArrayList<>();
        for (Quiz quiz : mQuizzes) {
            if (!quiz.isBookmarked()) {
                bookmarkedQuizzes.add(quiz);
            }
        }
        mView.loadQuizzes(bookmarkedQuizzes);
    }

    @Override
    public void onBookmarkStatusChange(Quiz quiz) {
        mDataHandler.addBookmark(quiz.getKey(), new DataHandler.Callback<Void>() {
            @Override
            public void onResponse(Void result) {

            }

            @Override
            public void onError() {

            }
        });
    }
}
