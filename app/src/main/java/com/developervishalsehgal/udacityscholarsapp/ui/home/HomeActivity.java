package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RadioGroup;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;
import com.developervishalsehgal.udacityscholarsapp.ui.notification.NotificationActivity;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View,
        QuizAdapter.QuizItemListener, NavigationView.OnNavigationItemSelectedListener {

    /* static constants for quiz filter */
    private static final int SLIDE_DOWN_ANIMATION_DURATION = 600;
    private static final int SLIDE_UP_ANIMATION_DURATION = 450;
    private static final int TRANSLATE_Y_POSITION_BEFORE_SLIDE_DOWN = -1000;
    private static final int ANIMATION_SLIDE_DOWN_TRANSLATE_Y = 0;
    private static final int ANIMATION_SLIDE_UP_TRANSLATE_Y = -1000;
    private static final int SLIDE_UP_DELAY_ON_CHECKED_CHANGED = 350;
    private static final int BACK_PRESS_DURATION = 3000;

    private QuizAdapter mQuizAdapter;

    private HomeContract.Presenter mPresenter;

    // UI Elements
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    // Reference of the quiz filter list layout
    private RadioGroup mHomeQuizListFilterRadioGroup;
    //////////////
    boolean twiceClicked = false;
    Snackbar snackbar;

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

        RecyclerView quizRecyclerView = findViewById(R.id.recyclerview_quizzes);
        quizRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        quizRecyclerView.setLayoutManager(linearLayoutManager);

        mQuizAdapter = new QuizAdapter(this);

        quizRecyclerView.setAdapter(mQuizAdapter);

        initQuizFilter();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(this);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

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

        // TODO remove below code, only for testing
        // Intent attemptQuizIntent = new Intent(this, AttemptQuizActivity.class);
        // attemptQuizIntent.putExtra(AttemptQuizContract.KEY_QUIZ_ID, quiz.getKey());
        // startActivity(attemptQuizIntent);
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
        Intent notificationActivityIntent = new Intent(this, NotificationActivity.class);
        startActivity(notificationActivityIntent);
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
    public void navigateToEditProfile() {
        // TODO: Navigate to edit profile activity
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

    @Override
    public void onBookmarkStatusChanged(Quiz quiz) {
        mPresenter.onBookmarkStatusChange(quiz);
    }

    /**
     * Initializes quiz filter
     */
    private void initQuizFilter() {
        //Set quiz filter button behavior
        (findViewById(R.id.quiz_filter_button)).setOnClickListener(view -> {
            if (mHomeQuizListFilterRadioGroup.getVisibility() == View.GONE)
                toggleQuizFilterView(true);
            else
                toggleQuizFilterView(false);
        });

        //Get reference to the quiz list filter layout and radio buttons
        mHomeQuizListFilterRadioGroup = findViewById(R.id.home_quiz_list_filter_radio_group);
        //Set radio group checked change listener so we can perform an action when a different
        //quiz filter is selected.
        mHomeQuizListFilterRadioGroup.setOnCheckedChangeListener(
                this::onQuizFilterItemCheckedChanged);
    }

    /**
     * Hides or shows the quiz filter view
     *
     * @param show if true, will show the view else will hide it.
     */
    private void toggleQuizFilterView(boolean show) {
        if (show) {
            mHomeQuizListFilterRadioGroup.setTranslationY(TRANSLATE_Y_POSITION_BEFORE_SLIDE_DOWN);
            mHomeQuizListFilterRadioGroup.setVisibility(View.VISIBLE);
            mHomeQuizListFilterRadioGroup.animate()
                    .setInterpolator(new FastOutSlowInInterpolator())
                    .setDuration(SLIDE_DOWN_ANIMATION_DURATION)
                    .translationY(ANIMATION_SLIDE_DOWN_TRANSLATE_Y);
        } else {
            mHomeQuizListFilterRadioGroup.animate()
                    .setInterpolator(new FastOutLinearInInterpolator())
                    .setDuration(SLIDE_UP_ANIMATION_DURATION)
                    .translationY(ANIMATION_SLIDE_UP_TRANSLATE_Y)
                    .withEndAction(() -> mHomeQuizListFilterRadioGroup.setVisibility(View.GONE)
                    );
        }
    }

    /**
     * This method will be invoked when quiz filter option is changed.
     *
     * @param radioGroup RadioGroup reference
     * @param id         id of the radio button
     */
    private void onQuizFilterItemCheckedChanged(RadioGroup radioGroup, int id) {
        //Hide the quiz filter view after few ms
        new Handler().postDelayed(() -> toggleQuizFilterView(false), SLIDE_UP_DELAY_ON_CHECKED_CHANGED);

        //Perform action based on selected quiz filter
        switch (id) {
            case R.id.radio_quiz_filter_all:
                mPresenter.onAllQuizSelected();
                break;
            case R.id.radio_quiz_filter_attempted:
                mPresenter.onAttemptedQuizSelected();
                break;
            case R.id.radio_quiz_filter_un_attempted:
                mPresenter.onUnAttemptedQuizSelected();
                break;
            case R.id.radio_quiz_filter_bookmarks:
                mPresenter.onBookmarkSelected();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (twiceClicked) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                snackbar.dismiss();

            } else {

                twiceClicked = true;


                snackbar = Snackbar.make(findViewById(R.id.homeactivitycoordinator), getResources().getString(R.string.home_back_btn_msg), Snackbar.LENGTH_LONG);
                final View snackbarView = snackbar.getView();
                snackbar.show();
                snackbarView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        snackbarView.getViewTreeObserver().removeOnPreDrawListener(this);
                        ((CoordinatorLayout.LayoutParams) snackbarView.getLayoutParams()).setBehavior(null);
                        return true;
                    }
                });

                new Handler().postDelayed(() -> twiceClicked = false, BACK_PRESS_DURATION);
            }

        }

    }
}