package com.developervishalsehgal.udacityscholarsapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class GetDetailsActivity extends AppCompatActivity {

    private Typeface mTypefaceReg, mTypefaceBold;

    private CircleImageView mCircleImageView;
    private EditText mUsernameEditText, mSlackHandleEditText;
    private TextView mUsername, mSlackHandle, mSelectTrack, mUserEmail;
    private Spinner mTrackSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details);

        mTypefaceReg = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.ttf");
        mTypefaceBold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.ttf");

        mCircleImageView = findViewById(R.id.add_profile_picture_image_view);

        mUsernameEditText = findViewById(R.id.get_name_edit_text);
        mUsernameEditText.setTypeface(mTypefaceBold);

        mSlackHandleEditText = findViewById(R.id.slack_handle_edit_text);
        mSlackHandleEditText.setTypeface(mTypefaceBold);

        mUsername = findViewById(R.id.get_name_label);
        mUsername.setTypeface(mTypefaceReg);

        mSlackHandle = findViewById(R.id.slack_handle_label);
        mSlackHandle.setTypeface(mTypefaceReg);

        mSelectTrack = findViewById(R.id.slack_track_label);
        mSelectTrack.setTypeface(mTypefaceReg);

        mUserEmail = findViewById(R.id.email_id_text_view);
        mUserEmail.setTypeface(mTypefaceBold);

        mTrackSpinner = findViewById(R.id.select_track_spinner);
    }
}
