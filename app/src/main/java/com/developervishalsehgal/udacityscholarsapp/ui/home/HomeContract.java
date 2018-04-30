package com.developervishalsehgal.udacityscholarsapp.ui.home;

import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

import java.util.List;

/**
 * Home screen contract. Keeps Home Screen View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface HomeContract {

    /**
     * Home View
     */
    interface View extends BaseView<Presenter> {
        void loadQuizzes(List<Quiz> quizzes);

        void navigateToQuizDesc(Quiz quiz);

        void navigateToScoreboard();

        void navigateToCreateQuiz();

        void navigateToNotifications();

        void navigateToResources();

        void navigateToSettings();

        void navigateToAboutScreen();

        void navigateToLogin();
    }

    /**
     * Home Presenter
     */
    interface Presenter extends BasePresenter {
        void onQuizClicked(Quiz quiz);

        void onLogoutClicked();

        void onAllQuizSelected();

        void onAttemptedQuizSelected();

        void onUnAttemptedQuizSelected();

        void onBookmarkQuizSelected();
    }

}
