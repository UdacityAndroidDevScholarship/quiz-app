package com.developervishalsehgal.udacityscholarsapp.ui.signin;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;

public class SignInPresenter implements SignInContract.Presenter {

    private SignInContract.View mView;
    private DataHandler mDataHandler;

    public SignInPresenter(SignInContract.View view) {
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();
        view.setPresenter(this);
    }

    @Override
    public void handleLoginRequest() {
        mView.showLoading();
        mView.startSignIn();
    }

    @Override
    public void handleLoginSuccess(String email, String displayName, Uri photoUrl) {
        mDataHandler.saveUserEmail(email);
        mDataHandler.saveUserName(displayName);
        mDataHandler.saveUserPic(photoUrl.toString());
        mView.hideLoading();
        mView.loginSuccess();
    }

    @Override
    public void handleLoginFailure(int statusCode, String message) {
        mView.hideLoading();
        mView.loginFailure(statusCode, message);
    }

    @Override
    public void start(@Nullable Bundle extras) {
        // Do nothing on start
    }

    @Override
    public void destroy() {
        this.mView = null;
    }
}
