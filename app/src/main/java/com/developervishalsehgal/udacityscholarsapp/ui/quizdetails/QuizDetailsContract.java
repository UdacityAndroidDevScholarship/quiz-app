package com.developervishalsehgal.udacityscholarsapp.ui.quizdetails;

import android.os.Bundle;

import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

public interface QuizDetailsContract {

    String KEY_QUIZ_ID = "quiz_id";

    /**
     * Quiz Details View
     */
    interface View extends BaseView<Presenter> {

        void enableStartButton();

        void showTitle(String quizTitle);

        void showAuthor(String quizAuthor);

        void showReleaseDate(String releaseDate);

        void showDeadline(String quizDeadline);

        void showDescription(String quizDescription);

        void showUserScore(String score, String maxMarks);

        void navigateToDiscussion(String quizId);

        void startQuiz(String quizId);

        void showInvalidInput();

        void onError();

        void dismissView();

    }

    /**
     * Quiz Details Presenter
     */
    interface Presenter extends BasePresenter {
        void onDiscussionClicked();

        void startQuizClicked();
    }

}
