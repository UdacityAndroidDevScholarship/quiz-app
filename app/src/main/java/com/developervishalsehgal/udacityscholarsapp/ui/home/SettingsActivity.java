package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.developervishalsehgal.udacityscholarsapp.R;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SettingsTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setUpToolbar();
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_settings_activity);

        toolbar.setTitle(R.string.settings);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
