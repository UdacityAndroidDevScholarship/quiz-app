package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View,
        QuizAdapter.QuizItemListener {

    private QuizAdapter mQuizAdapter;

    private HomeContract.Presenter mPresenter;

    // UI Elements
    private DrawerLayout mDrawerLayout;
    //////////////

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Injecting presenter reference
        PresenterInjector.injectHomePresenter(this);

        initializeUI();

        mPresenter.start(getIntent().getExtras());
    }

    private void initializeUI() {
        Toolbar toolbar = findViewById(R.id.toolbarrnav);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_udacity));
        }

        RecyclerView mQuizRecyclerView = findViewById(R.id.recyclerview_quizzes);
        mQuizRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mQuizRecyclerView.setLayoutManager(linearLayoutManager);

        mQuizAdapter = new QuizAdapter(this);

        mQuizRecyclerView.setAdapter(mQuizAdapter);

        mDrawerLayout = findViewById(R.id.drawer_layout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                    mDrawerLayout.closeDrawer(Gravity.START);
                } else {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
                break;
            case R.id.logout:
                // TODO: Show a confirmation {@link AlertDialog} here. When user cliks OK. call
                // TODO: mPresenter.logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadQuizzes(List<Quiz> quizzes) {
        mQuizAdapter.loadQuizzes(quizzes);
    }

    @Override
    public void onQuizLoadError() {
        // TODO: show an alert or toast saying "quiz can't be loaded at the moment, check network connection and try again"
    }

    @Override
    public void navigateToQuizDesc(Quiz quiz) {
        // TODO: Navigate to QuizDescription Activity, use the quiz object above to extract quiz
        // TODO: details etc and pass as intent parameter.
    }

    @Override
    public void navigateToScoreboard() {
        // TODO: Navigate to Scoreboard screen
    }

    @Override
    public void navigateToCreateQuiz() {
        // TODO: Navigate to Create Quiz screen
    }

    @Override
    public void navigateToNotifications() {
        // TODO: Navigate to Notifications screen
    }

    @Override
    public void navigateToResources() {
        // TODO: Navigate to Resources screen / tab
    }

    @Override
    public void navigateToSettings() {
        // TODO: Navigate to Settings screen
    }

    @Override
    public void navigateToAboutScreen() {
        // TODO: Navigate to About screen
    }

    @Override
    public void navigateToLogin() {
        // TODO: This should only be called when user clicks on logout. Kill current activity
        // TODO: and launch login activity
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        // TODO: Show progress bar / dialog here
    }

    @Override
    public void hideLoading() {
        // TODO: Hide progress bar / dialog here
    }

    @Override
    public void onQuizClicked(Quiz quiz) {
        mPresenter.onQuizClicked(quiz);
    }
}
