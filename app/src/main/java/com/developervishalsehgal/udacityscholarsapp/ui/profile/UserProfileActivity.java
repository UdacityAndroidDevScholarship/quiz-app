package com.developervishalsehgal.udacityscholarsapp.ui.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.User;
import com.developervishalsehgal.udacityscholarsapp.databinding.ActivityUserProfileBinding;

/**
 * Profile activity for the app.
 */
public class UserProfileActivity extends AppCompatActivity implements ProfileContract.View {

    //Data Binding
    ActivityUserProfileBinding mUserProfileBinding;

    private User mUser;

    public static final String[] UDACITY_COURSE_TRACK = {
            "Android Beginner Course",
            "Android Developer Course",
            "Web Developer Beginner Course",
            "Web Developer Intermediate Course",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);

        //Sample Data
        mUser = new User("xxx@gmail.com", "http://i.imgur.com/hezkORi.png", "myName",
                "@slackHandle", UDACITY_COURSE_TRACK[1]);

        mUserProfileBinding.setUser(mUser);

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
