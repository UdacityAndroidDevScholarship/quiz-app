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

    int NAVIGATION_SCOREBOARD = 0;
    int NAVIGATION_CREATE_QUIZ = 1;
    int NAVIGATION_NOTIFICATIONS = 2;
    int NAVIGATION_RESOURCES = 3;
    int NAVIGATION_SETTINGS = 4;
    int NAVIGATION_ABOUT = 5;
    int NAVIGATION_EDIT_PROFILE = 6;

    /**
     * Home View
     */
    interface View extends BaseView<Presenter> {
        void loadQuizzes(List<Quiz> quizzes);

        void onQuizLoadError();

        void navigateToQuizDesc(Quiz quiz);

        void navigateToScoreboard();

        void navigateToCreateQuiz();

        void navigateToNotifications();

        void navigateToResources();

        void navigateToSettings();

        void navigateToAboutScreen();

        void navigateToEditProfile();
    }

    /**
     * Home Presenter
     */
    interface Presenter extends BasePresenter {
        void onQuizClicked(Quiz quiz);

        void onNavigationItemSelected(int navItemSpecifier);

        void onLogoutClicked();

        void onAllQuizSelected();

        void onAttemptedQuizSelected();

        void onUnAttemptedQuizSelected();

        void onBookmarkSelected();

        void onBookmarkStatusChange(Quiz quiz);
    }

}
