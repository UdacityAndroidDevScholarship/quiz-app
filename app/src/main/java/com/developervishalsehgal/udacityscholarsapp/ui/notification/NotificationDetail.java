package com.developervishalsehgal.udacityscholarsapp.ui.notification;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.developervishalsehgal.udacityscholarsapp.R;

public class NotificationDetail extends AppCompatActivity {

    private TextView mNotificationName;
    private TextView mNotificationDate;
    private TextView mNotificationDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        Intent getNotification = getIntent();

        String notificationTitle = getNotification.getStringExtra("title");
        String notificationName = getNotification.getStringExtra("name");
        String notificationDate = getNotification.getStringExtra("date");
        String notificationDescription = getNotification.getStringExtra("description");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(notificationTitle);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mNotificationName = findViewById(R.id.tv_notification_detail_name);
        mNotificationDate = findViewById(R.id.tv_notification_detail_date);
        mNotificationDescription = findViewById(R.id.tv_notification_detail_description);

        mNotificationName.setText(notificationName);
        mNotificationDate.setText(notificationDate);
        mNotificationDescription.setText(notificationDescription);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
