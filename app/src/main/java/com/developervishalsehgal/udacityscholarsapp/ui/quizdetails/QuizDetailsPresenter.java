package com.developervishalsehgal.udacityscholarsapp.ui.quizdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

public class QuizDetailsPresenter implements QuizDetailsContract.Presenter {

    private QuizDetailsContract.View mView;
    private DataHandler mDataHandler;

    private String mCurrentQuizId;

    public QuizDetailsPresenter(QuizDetailsContract.View view) {
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();

        mView.setPresenter(this);
    }

    @Override
    public void onDiscussionClicked() {
        mView.navigateToDiscussion(mCurrentQuizId);
    }

    @Override
    public void startQuizClicked() {
        mView.startQuiz(mCurrentQuizId);
    }

    @Override
    public void start(@Nullable Bundle extras) {
        if (extras == null || !extras.containsKey(QuizDetailsContract.KEY_QUIZ_ID)) {
            mView.showInvalidInput();
            return;
        }

        String quizId = extras.getString(QuizDetailsContract.KEY_QUIZ_ID);
        this.mCurrentQuizId = quizId;

        mView.showLoading();
        mDataHandler.fetchQuizById(quizId, new DataHandler.Callback<Quiz>() {
            @Override
            public void onResponse(Quiz result) {

                mView.showAuthor(result.getCreatorName());
                mView.showDeadline(result.getDeadline());
                mView.showDescription(result.getDescription());
                mView.showReleaseDate(result.getLastModified());
                mView.showTitle(result.getTitle());

                if (result.isAttempted()) {
                    // User score is stored in rated-by field
                    mView.showUserScore(String.valueOf(result.getRatedBy()),
                            String.valueOf(result.getMaxMarks()));
                } else {
                    mView.enableStartButton();
                }
                mView.hideLoading();
            }

            @Override
            public void onError() {
                mView.onError();
                mView.hideLoading();
            }
        });
    }

    @Override
    public void destroy() {
        this.mView = null;
        this.mDataHandler = null;
    }
}
