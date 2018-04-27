package com.developervishalsehgal.udacityscholarsapp.ui.profile;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.developervishalsehgal.udacityscholarsapp.R;

/**
 * Profile activity for the app.
 */
public class UserProfileActivity extends AppCompatActivity implements ProfileContract.View {

    private ImageButton mEditProfileButton, mEditDoneButton;
    private TextInputLayout mNameTextInputLayout;
    private TextInputLayout mSlackTextInputLayout;
    private TextView mNameTextView;
    private Button mNextButton;
    private TextView mSlackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mEditProfileButton = (ImageButton) findViewById(R.id.edit_profile_button);
        mEditDoneButton = (ImageButton) findViewById(R.id.edit_profile_done_button);
        mNameTextView = (TextView) findViewById(R.id.text_name);
        mSlackTextView = (TextView) findViewById(R.id.text_slack_handle);
        mNextButton = (Button) findViewById(R.id.next_button);

        mNameTextInputLayout = (TextInputLayout) findViewById(R.id.edittext_name);
        mSlackTextInputLayout = (TextInputLayout) findViewById(R.id.edittext_slack_handle);

        mEditProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNameTextView.setVisibility(View.INVISIBLE);
                mSlackTextView.setVisibility(View.INVISIBLE);
                mNextButton.setVisibility(View.GONE);

                mNameTextInputLayout.setVisibility(View.VISIBLE);
                mSlackTextInputLayout.setVisibility(View.VISIBLE);
                mEditDoneButton.setVisibility(View.VISIBLE);
            }
        });

        mEditDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNameTextView.setVisibility(View.VISIBLE);
                mNameTextView.setText(mNameTextInputLayout.getEditText().getText().toString());

                mSlackTextView.setVisibility(View.VISIBLE);
                mSlackTextView.setText(mSlackTextInputLayout.getEditText().getText().toString());

                mSlackTextInputLayout.setVisibility(View.GONE);
                mNameTextInputLayout.setVisibility(View.GONE);
                mEditDoneButton.setVisibility(View.GONE);

                mNextButton.setVisibility(View.VISIBLE);
            }
        });
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
