package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.developervishalsehgal.udacityscholarsapp.R;

public class QuizHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Add Recycler View for Quizzes
        RecyclerView quizRecyclerView = (RecyclerView) findViewById(R.id.rv_quiz_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        quizRecyclerView.setLayoutManager(linearLayoutManager);
        quizRecyclerView.setAdapter(new QuizRecyclerAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz_home, menu);
        return true;
    }
}
