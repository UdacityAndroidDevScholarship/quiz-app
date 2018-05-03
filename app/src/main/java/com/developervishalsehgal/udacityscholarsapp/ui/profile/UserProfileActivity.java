package com.developervishalsehgal.udacityscholarsapp.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import com.developervishalsehgal.udacityscholarsapp.R;


/**
 * Profile activity for the app.
 */
public class UserProfileActivity extends AppCompatActivity implements ProfileContract.View, View.OnClickListener {

    private ImageView profile_picture;
    private ImageView cover_picture;
    private Spinner track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profile_picture = findViewById(R.id.profile_picture);
        cover_picture = findViewById(R.id.header_cover_image);
        track = findViewById(R.id.spinner);

        Intent sourceIntent = getIntent();

        if (sourceIntent != null) {
            Bundle params = sourceIntent.getExtras();

            if (params != null ) {
                if (params.containsKey("profile_path")) {
                    profile_picture.setImageResource(params.getInt("profile_path"));
                }
                if (params.containsKey("cover_path")) {
                    profile_picture.setImageResource(params.getInt("cover_path"));
                }
            }

        }
        track.setEnabled(false);
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

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.profile_picture) {

            Intent intent = new Intent(this,com.developervishalsehgal.udacityscholarsapp.ui.avatars.avatar_selection.class);
            startActivity(intent);

        } else if (id == R.id.header_cover_image) {

            Intent intent = new Intent(this,com.developervishalsehgal.udacityscholarsapp.ui.coverimage.cover_picture_selection.class);
            startActivity(intent);
        } else if (id == R.id.submit_details) {
            // TODO implement this function to save the changes made to the details
        }
    }
}
