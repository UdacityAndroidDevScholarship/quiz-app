package com.developervishalsehgal.udacityscholarsapp.ui.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.developervishalsehgal.udacityscholarsapp.R;

public class QuizDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details);

        toolbar = findViewById(R.id.toolbar_quiz_details);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Quiz / Discussion");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
