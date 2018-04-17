package com.developervishalsehgal.udacityscholarsapp.ui.signin;

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

        void loginFailure();

    }

    /**
     * SignIn Presenter
     */
    interface Presenter extends BasePresenter {

        void handleLoginRequest(Bundle data);

    }

}
