package com.developervishalsehgal.udacityscholarsapp.ui.quizattempt;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.data.models.Question;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class AttemptQuizPresenter implements AttemptQuizContract.Presenter {

    private AttemptQuizContract.View mView;
    private DataHandler mDataHandler;

    // Local variables
    private int mPointer = 0;
    private Quiz mSelectedQuiz;

    private List<Question> mQuestions;
    private List<Question> mUserAttempts;

    public AttemptQuizPresenter(AttemptQuizContract.View view) {
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();

        this.mView.setPresenter(this);
    }

    @Override
    public void onNextClicked(Question userAttempt) {

        if (mPointer == (mQuestions.size() - 1)) {
            // submit button clicked
            mView.showSubmitConfirmation();
        } else {
            mPointer++;
            mView.loadQuestion(mUserAttempts.get(mPointer));
        }
    }

    @Override
    public void onPreviousClicked() {
        if (mPointer == 0) {
            // First question
        } else {
            mPointer--;
            mView.loadQuestion(mUserAttempts.get(mPointer));
        }
    }

    @Override
    public void onSubmitClicked() {

    }

    @Override
    public void start(@Nullable Bundle extras) {

        if (extras == null || !extras.containsKey(AttemptQuizContract.KEY_QUIZ_ID)) {
            mView.showInvalidInput();
            return;
        }

        String quizId = extras.getString(AttemptQuizContract.KEY_QUIZ_ID);

        mView.showLoading();
        mDataHandler.fetchQuizById(quizId, new DataHandler.Callback<Quiz>() {
            @Override
            public void onResponse(Quiz result) {
                displayQuiz(result);
            }

            @Override
            public void onError() {
                mView.showError();
            }
        });

    }

    private void displayQuiz(Quiz currentQuiz) {
        mSelectedQuiz = currentQuiz;
        mQuestions = new ArrayList<>(mSelectedQuiz.getQuestions().values());
        mUserAttempts = new ArrayList<>(mSelectedQuiz.getQuestions().values());

        // Clear all is-correct flags from user attempts, it will be used to store user's answers
        for (Question question : mUserAttempts) {
            question.resetOptions();
        }

        // Since we are currently in first question
        mView.disablePreviousButton();

        mView.loadQuestion(mUserAttempts.get(mPointer));
    }

    @Override
    public void destroy() {
        this.mView = null;
        this.mDataHandler = null;
    }

    private void updateQuestionStatus(){

        mView.loadAttemptedStatusText(null);
    }
}
