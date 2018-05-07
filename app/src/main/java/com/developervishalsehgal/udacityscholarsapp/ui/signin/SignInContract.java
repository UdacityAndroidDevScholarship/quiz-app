package com.developervishalsehgal.udacityscholarsapp.ui.signin;

import android.net.Uri;
import android.os.Bundle;

import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

/**
 * Sign In Contract contract. Keeps SignIn Screen View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface SignInContract {

    /**
     * SignIn View
     */
    interface View extends BaseView<Presenter> {

        void loginSuccess();

        void loginFailure(int statusCode, String message);

        void startSignIn();

        void navigateToHome();

        void navigateToProfile();
    }

    /**
     * SignIn Presenter
     */
    interface Presenter extends BasePresenter {

        void handleLoginRequest();

        void handleLoginSuccess(String email, String displayName, Uri photoUrl);

        void handleLoginFailure(int statusCode, String message);
    }

}
