package com.developervishalsehgal.udacityscholarsapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GetDetailsActivity extends AppCompatActivity {

    private Typeface mTypefaceReg, mTypefaceBold;

    private CircleImageView mCircleImageView;
    private EditText mUsernameEditText, mSlackHandleEditText;
    private TextView mUsername, mSlackHandle, mSelectTrack, mUserEmail;
    private Spinner mTrackSpinner;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details);

        //Initialising the typefaces
        mTypefaceReg = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.ttf");
        mTypefaceBold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.ttf");

        //Initialising all the views by referencing them from the XML
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

        mSubmitButton = findViewById(R.id.get_details_submit_button);
        mSubmitButton.setTypeface(mTypefaceBold);

        mTrackSpinner = findViewById(R.id.select_track_spinner);
        mTrackSpinner.setPrompt("Select Track");

        //Spinner drop down elements.
        List<String> trackList = new ArrayList<>();
        trackList.add("Select Track");
        trackList.add("Android Developer Beginner's Track");
        trackList.add("Android Developer Track");
        trackList.add("Front-End Web Developer Track");
        trackList.add("Full Stack Web Developer Track");

        //Creating an adapter for the Select Track Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, trackList);

        //Selecting the Drop Down style
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Attaching the data to the spinner.
        mTrackSpinner.setAdapter(spinnerAdapter);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}
