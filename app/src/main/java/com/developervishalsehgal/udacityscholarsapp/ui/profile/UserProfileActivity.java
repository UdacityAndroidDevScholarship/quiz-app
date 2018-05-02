package com.developervishalsehgal.udacityscholarsapp.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;
import com.developervishalsehgal.udacityscholarsapp.ui.home.HomeActivity;

/**
 * Profile activity for the app.
 */
public class UserProfileActivity extends AppCompatActivity implements ProfileContract.View
        , View.OnClickListener {

    // UI Elements
    private ImageView mImgUserPic;
    private EditText mEtSlackHandle;
    private EditText mEtCourseTrack;
    private ImageButton mBtnEditProfile;
    private EditText mEtUserName;
    private TextView mTvUserEmail;
    private ProgressBar mProgressBar;

    private ProfileContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        initializeUI();

        // Injecting presenter
        PresenterInjector.injectProfilePresenter(this);

        mPresenter.start(getIntent().getExtras());
    }

    private void initializeUI() {
        mImgUserPic = findViewById(R.id.img_user_pic);
        mEtSlackHandle = findViewById(R.id.tiet_slack_handle);
        mEtCourseTrack = findViewById(R.id.et_scholarship_track);
        mBtnEditProfile = findViewById(R.id.btn_edit_profile);
        mEtUserName = findViewById(R.id.et_user_name);
        mTvUserEmail = findViewById(R.id.tv_email_profile);

        Button mBtnProceed = findViewById(R.id.btn_profile_proceed);
        mBtnProceed.setOnClickListener(this);

        mProgressBar = findViewById(R.id.pb_profile);
        mProgressBar.setIndeterminate(true);
    }

    @Override
    public void loadUserPic(String picUrl) {
        if(picUrl == null){
            return;
        }
        Glide.with(this).load(picUrl).into(mImgUserPic);
        // GlideApp.with(this).load(picUrl).into(mImgUserPic);
    }

    @Override
    public void loadUserName(String userName) {
        if(userName == null){
            return;
        }
        mEtUserName.setText(userName);
    }

    @Override
    public void loadSlackHandle(String slackHandle) {
        if (slackHandle != null && !slackHandle.isEmpty()) {
            mEtSlackHandle.setText(slackHandle);
        }
    }

    @Override
    public void loadEmailAddress(String emailAddress) {
        mTvUserEmail.setText(emailAddress);
    }

    @Override
    public void loadUserTrack(String userTrack) {
        if(userTrack == null){
            return;
        }
        mEtCourseTrack.setText(userTrack);
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
        // Navigate to home activity
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
        this.finish();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_profile_proceed:
                mPresenter.saveProfile("", mEtSlackHandle.getText().toString()
                        , mEtCourseTrack.getText().toString());
                break;
            default:
                break;
        }
    }
}
