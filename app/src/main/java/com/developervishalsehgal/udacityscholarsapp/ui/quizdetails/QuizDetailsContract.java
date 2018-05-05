package com.developervishalsehgal.udacityscholarsapp.ui.quizdetails;

import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

public interface QuizDetailsContract {

    /**
     * Quiz Details View
     */
    interface View extends BaseView<Presenter> {

        void enableStartButton(boolean isAttempted);

        void showTitle(String quizTitle);

        void showAuthor(String quizAuthor);

        void showReleaseDate(String releaseDate);

        void showDeadline(String quizDeadline);

        void showDescription(String quizDescription);

        void showUserScore(String scoreOutOfMaxMarks);

        void navigateToDiscussion();

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
