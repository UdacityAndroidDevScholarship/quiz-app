package com.developervishalsehgal.udacityscholarsapp.ui.quizattempt;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.data.models.Question;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.data.models.QuizAttempted;

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

    private boolean mIsEvaluated = false;

    public AttemptQuizPresenter(AttemptQuizContract.View view) {
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();

        this.mView.setPresenter(this);
    }

    @Override
    public void onNextClicked() {

        if (mPointer < mQuestions.size()) {

            if (mPointer == (mQuestions.size() - 1)) {
                // Last question reached. Submit button clicked
                if (!mIsEvaluated) {
                    mView.showSubmitConfirmation();
                } else {
                    mView.dismissView();
                }
            } else {

                mPointer++;

                if (!mIsEvaluated) {
                    mView.loadQuestion(mUserAttempts.get(mPointer));
                } else {
                    mView.loadQuestionForReview(mUserAttempts.get(mPointer), mQuestions.get(mPointer));
                }

                // Last question reached, show submit button
                if (mPointer == (mQuestions.size() - 1)) {
                    mView.showSubmitButton();
                }

            }
            mView.enablePreviousButton();
            updateQuestionStatus();
        }
    }

    @Override
    public void onReviewClicked() {
        mIsEvaluated = true;
        mPointer = 0;
        mView.disablePreviousButton();
        mView.loadQuestionForReview(mQuestions.get(mPointer), mUserAttempts.get(mPointer));
        mView.disablePreviousButton();
        mView.showNextButton();
        updateQuestionStatus();
    }

    @Override
    public void onPreviousClicked() {
        if (mPointer > 0) {
            // First question
            mPointer--;

            if (!mIsEvaluated) {
                mView.loadQuestion(mUserAttempts.get(mPointer));
            } else {
                mView.loadQuestionForReview(mQuestions.get(mPointer), mUserAttempts.get(mPointer));
            }

            if (mPointer == 0) {
                mView.disablePreviousButton();
            }

            mView.showNextButton();
            updateQuestionStatus();
        }
    }

    @Override
    public void onSubmitClicked() {
        if (!mIsEvaluated) {

            int maxMarks = mSelectedQuiz.getMaxMarks();

            int userScore = 0;

            // Evaluating user's score based on performance
            for (Question userAttempt : mUserAttempts) {
                if (mQuestions.contains(userAttempt)) {
                    userScore += userAttempt.getMarks();
                }
            }

            int finalUserScore = userScore;

            double userPercentage = 100 * (((double) finalUserScore) / maxMarks);

            QuizAttempted quizAttempted = new QuizAttempted();
            quizAttempted.setKey(mSelectedQuiz.getKey());
            quizAttempted.setQuizId(mSelectedQuiz.getKey());
            quizAttempted.setLesson(mSelectedQuiz.getLesson());
            quizAttempted.setMaxMarks(maxMarks);
            quizAttempted.setPercentage((int) userPercentage);
            quizAttempted.setQuizTitle(mSelectedQuiz.getTitle());
            quizAttempted.setScore(userScore);

            mDataHandler.updateMyAttemptedQuizzes(quizAttempted, new DataHandler.Callback<Void>() {
                @Override
                public void onResponse(Void result) {
                    mView.loadResultSummary(finalUserScore, maxMarks, userPercentage);
                }

                @Override
                public void onError() {
                    mView.showError();
                }
            });

        } else {
            mView.dismissView();
        }
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
                mView.hideLoading();
                displayQuiz(result);
            }

            @Override
            public void onError() {
                mView.hideLoading();
                mView.showError();
            }
        });

    }

    @SuppressWarnings("unchecked")
    private void displayQuiz(Quiz currentQuiz) {
        mSelectedQuiz = currentQuiz;
        mQuestions = new ArrayList<>(mSelectedQuiz.getQuestions().values());
        mUserAttempts = new ArrayList<>();
        // mUserAttempts = (List<Question>) (new ArrayList<>(mSelectedQuiz.getQuestions().values())).clone();

        // Clear all is-correct flags from user attempts, it will be used to store user's answers
        for (Question question : mQuestions) {
            Question copyQuestion = new Question(question);
            copyQuestion.resetOptions();
            mUserAttempts.add(copyQuestion);
        }

        // Since we are currently in first question
        mView.disablePreviousButton();

        mView.loadQuestion(mUserAttempts.get(mPointer));

        mView.loadTitle(mSelectedQuiz.getTitle());
    }

    @Override
    public void destroy() {
        this.mView = null;
        this.mDataHandler = null;
    }

    private void updateQuestionStatus() {
        mView.loadAttemptedStatusText(mPointer + 1, mQuestions.size());
    }
}
