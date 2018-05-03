package com.developervishalsehgal.udacityscholarsapp.ui.avatars;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.developervishalsehgal.udacityscholarsapp.R;


public class avatar_selection extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_selection);

    }

    @Override
    public void onClick(View view) {

        final Intent intent = new Intent(this,com.developervishalsehgal.udacityscholarsapp.ui.profile.UserProfileActivity.class);

        int id = view.getId();
        if (id == R.id.pic1) {

            new MaterialDialog.Builder(this)
                    .title("Are you sure?")
                    .content("Selected image would be chosen as your new profile picture")
                    .positiveText("YES")
                    .negativeText("NO")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {

                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            intent.putExtra("profile_path",R.drawable.avatar_1);
                            startActivity(intent);
                        }
                    })
                    .show();

        } else if (id == R.id.pic2) {
            new MaterialDialog.Builder(this)
                    .title("Are you sure?")
                    .content("Selected image would be chosen as your new profile picture")
                    .positiveText("YES")
                    .negativeText("NO")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {

                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            intent.putExtra("profile_path",R.drawable.avatar_2);
                            startActivity(intent);
                        }
                    })
                    .show();

        } else if (id == R.id.pic3) {

            new MaterialDialog.Builder(this)
                    .title("Are you sure?")
                    .content("Selected image would be chosen as your new profile picture")
                    .positiveText("YES")
                    .negativeText("NO")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {

                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            intent.putExtra("profile_path",R.drawable.avatar_3);
                            startActivity(intent);
                        }
                    })
                    .show();

        } else if (id == R.id.pic4) {

            new MaterialDialog.Builder(this)
                    .title("Are you sure?")
                    .content("Selected image would be chosen as your new profile picture")
                    .positiveText("YES")
                    .negativeText("NO")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {

                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            intent.putExtra("profile_path",R.drawable.avatar_4);
                            startActivity(intent);
                        }
                    })
                    .show();

        }
    }
}
