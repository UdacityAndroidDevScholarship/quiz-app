package com.developervishalsehgal.udacityscholarsapp.ui.quiz;

import com.developervishalsehgal.udacityscholarsapp.data.models.Question;
import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

/**
 * Quiz screen contract. Keeps Quiz View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface QuizContract {

    /**
     * Quiz View
     */
    interface View extends BaseView<Presenter> {

        void enablePreviousButton();

        void disablePreviousButton();

        void showSubmitButton();

        void loadQuestion(Question question);

        void loadQuestionForReview(Question question, Question attemptedQuestion);

        void loadAttemptedStatusText(String attemptedQuestionStatus);

        void loadTitle(String quizTitle);

        void loadResultSummary(int score, int total, double percentage);

        void dismissView();

    }

    /**
     * Quiz Presenter
     */
    interface Presenter extends BasePresenter {
        void onNextClicked();

        void onPreviousClicked();
    }

}
