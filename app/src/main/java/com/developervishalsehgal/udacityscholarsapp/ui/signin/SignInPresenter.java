package com.developervishalsehgal.udacityscholarsapp.ui.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;

public class SignInPresenter implements SignInContract.Presenter {

    private SignInContract.View mView;
    private DataHandler mDataHandler;

    public SignInPresenter(SignInContract.View view){
        this.mView = view;
    }

    @Override
    public void handleLoginRequest(Bundle data) {

        mDataHandler.saveUserName(data.getString("username"));

        mView.showNetworkError();
        mView.showLoading();

        // if login fails
        mView.loginFailure();

    }

    @Override
    public void start(@Nullable Bundle extras) {

    }

    @Override
    public void destroy() {

    }
}
