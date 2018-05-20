package com.developervishalsehgal.udacityscholarsapp.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.developervishalsehgal.udacityscholarsapp.R;

public class SettingsActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SettingsTheme);
        setContentView(R.layout.activity_settings);
        toolbar = findViewById(R.id.settings_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setNavigationOnClickListener(v -> NavUtils.navigateUpFromSameTask(SettingsActivity.this));
    }
}
