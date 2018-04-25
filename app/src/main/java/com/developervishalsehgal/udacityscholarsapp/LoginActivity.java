package com.developervishalsehgal.udacityscholarsapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Typeface quicksand_bold, quicksand_reg;

    private Button signInButton, skipButton;
    private TextView udacityText, quizText, poweredByText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        quicksand_bold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.ttf");
        quicksand_reg = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.ttf");

        signInButton = findViewById(R.id.sign_in_button_login);
        signInButton.setTypeface(quicksand_reg);
        skipButton = findViewById(R.id.skip_login);
        skipButton.setTypeface(quicksand_bold);

        udacityText = findViewById(R.id.udacity_login_tv);
        udacityText.setTypeface(quicksand_reg);
        quizText = findViewById(R.id.quiz_login_tv);
        quizText.setTypeface(quicksand_bold);
        poweredByText = findViewById(R.id.powered_by_tv);
        poweredByText.setTypeface(quicksand_reg);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GetDetailsActivity.class));
                finish();
            }
        });
    }
}
