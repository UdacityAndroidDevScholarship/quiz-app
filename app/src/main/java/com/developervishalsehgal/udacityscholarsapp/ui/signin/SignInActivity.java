package com.developervishalsehgal.udacityscholarsapp.ui.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.developervishalsehgal.udacityscholarsapp.R;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private SignInContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

    }

    @Override
    public void loginSuccess() {
        // TODO Login has succeeded, notify user and navigate to home activity
    }

    @Override
    public void loginFailure() {
        // TODO Login failed, notify user
    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void setPresenter(SignInContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        // TODO show progress bar / dialog here
    }

    @Override
    public void hideLoading() {
        // TODO hide progress bar / dialog here
    }
}
