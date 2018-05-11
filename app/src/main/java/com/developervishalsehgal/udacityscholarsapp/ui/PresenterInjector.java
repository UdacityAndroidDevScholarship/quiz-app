package com.developervishalsehgal.udacityscholarsapp.ui;

import com.developervishalsehgal.udacityscholarsapp.ui.discussion.QuizDiscussionActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.discussion.QuizDiscussionAdapter;
import com.developervishalsehgal.udacityscholarsapp.ui.discussion.QuizDiscussionContract;
import com.developervishalsehgal.udacityscholarsapp.ui.discussion.QuizDiscussionPresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.home.HomeContract;
import com.developervishalsehgal.udacityscholarsapp.ui.home.HomePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.profile.ProfileContract;
import com.developervishalsehgal.udacityscholarsapp.ui.profile.ProfilePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.quizattempt.AttemptQuizContract;
import com.developervishalsehgal.udacityscholarsapp.ui.quizattempt.AttemptQuizPresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.signin.SignInContract;
import com.developervishalsehgal.udacityscholarsapp.ui.signin.SignInPresenter;

/**
 * Encapsulates creation of all Presenters
 */
public class PresenterInjector {

    public static void injectSignInPresenter(SignInContract.View signInView) {
        new SignInPresenter(signInView);
    }

    public static void injectProfilePresenter(ProfileContract.View profileView) {
        new ProfilePresenter(profileView);
    }

    public static void injectHomePresenter(HomeContract.View homeView) {
        new HomePresenter(homeView);
    }

    public static void injectQuizDiscussionPresenter(QuizDiscussionContract.View quizDiscussionView) {
        new QuizDiscussionPresenter(quizDiscussionView);

    public static void injectQuizAttemptPresenter(AttemptQuizContract.View attemptQuizView) {
        new AttemptQuizPresenter(attemptQuizView);

    }
}
