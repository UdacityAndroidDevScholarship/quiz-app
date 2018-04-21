package com.developervishalsehgal.udacityscholarsapp.ui.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.developervishalsehgal.udacityscholarsapp.R;

/**
 * Profile activity for the app.
 */
public class UserProfileActivity extends AppCompatActivity implements ProfileContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    @Override
    public void loadUserPic(String picUrl) {

    }

    @Override
    public void loadUserName(String userName) {

    }

    @Override
    public void loadSlackHandle(String slackHandle) {

    }

    @Override
    public void loadEmailAddress(String emailAddress) {

    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
