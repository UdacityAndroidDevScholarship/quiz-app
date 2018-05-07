package com.developervishalsehgal.udacityscholarsapp.ui;

import com.developervishalsehgal.udacityscholarsapp.ui.profile.ProfileContract;
import com.developervishalsehgal.udacityscholarsapp.ui.profile.ProfilePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.signin.SignInContract;
import com.developervishalsehgal.udacityscholarsapp.ui.signin.SignInPresenter;

/**
 * Encapsulates creation of all Presenters
 */
public class PresenterInjector {

    public static void injectSignInPresenter(SignInContract.View signInView){
        new SignInPresenter(signInView);
    }

    public static void injectProfilePresenter(ProfileContract.View profileView){
        new ProfilePresenter(profileView);
    }

}
