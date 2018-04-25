package com.developervishalsehgal.udacityscholarsapp.ui.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.developervishalsehgal.udacityscholarsapp.R;

/**
 * Profile activity for the app.
 */
public class UserProfileActivity extends AppCompatActivity implements ProfileContract.View {

    private ProfileContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // TODO Inject presenter here

        findViewById(R.id.btn_profile_proceed).setOnClickListener(v -> {
            // TODO replace these values with actual values from text fields
            mPresenter.saveProfile("", "@Kaushal", "Yahoo it saved");
        });
    }

    @Override
    public void loadUserPic(String picUrl) {
        // TODO use picasso or glide to load image url into image view
    }

    @Override
    public void loadUserName(String userName) {
        // TODO display user name in UI
    }

    @Override
    public void loadSlackHandle(String slackHandle) {
        // TODO display slack handle in UI
    }

    @Override
    public void loadEmailAddress(String emailAddress) {
        // TODO display user email in UI
    }

    @Override
    public void loadUserTrack(String userTrack) {
        // TODO display track in UI if any
    }

    @Override
    public void onPictureUploadError() {
        Toast.makeText(this, getString(R.string.error_uploading_picture), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveError() {
        Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProfileSaved() {
        Toast.makeText(this, getString(R.string.profile_saved_successfully), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        // TODO show progress bar here
    }

    @Override
    public void hideLoading() {
        // TODO hide progress bar here
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
