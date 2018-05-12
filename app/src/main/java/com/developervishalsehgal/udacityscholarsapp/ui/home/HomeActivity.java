package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.settings.SettingsActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;
import com.developervishalsehgal.udacityscholarsapp.ui.notification.NotificationActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.quizdetails.QuizDetailsActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.quizdetails.QuizDetailsContract;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View,
        QuizAdapter.QuizItemListener {

    /* static constants for quiz filter */
    private static final int QUIZ_FILTER_VIEW_SLIDE_DOWN_ANIMATION_DURATION = 600;
    private static final int QUIZ_FILTER_VIEW_SLIDE_UP_ANIMATION_DURATION = 450;
    private static final int QUIZ_FILTER_VIEW_TRANSLATE_Y_POSITION_BEFORE_SLIDE_DOWN = -1000;
    private static final int QUIZ_FILTER_VIEW_ANIMATION_SLIDE_DOWN_TRANSLATE_Y = 0;
    private static final int QUIZ_FILTER_VIEW_ANIMATION_SLIDE_UP_TRANSLATE_Y = -1000;
    private static final int QUIZ_FILTER_VIEW_SLIDE_UP_DELAY_ON_CHECKED_CHANGED = 350;

    private QuizAdapter mQuizAdapter;

    private HomeContract.Presenter mPresenter;

    private RelativeLayout splashLayout;
    private CoordinatorLayout homeLayout;
    private ProgressBar splashScreenProgress;

    private ValueAnimator splashProgressLoading;
    private Animation recyclerViewLoading;

    private RecyclerView quizRecyclerView;

    // UI Elements
    private DrawerLayout mDrawerLayout;
    private RecyclerView mQuizRecyclerView;
    //Reference of the quiz filter list layout
    private RadioGroup mHomeQuizListFilterRadioGroup;
    //////////////
    private TextView mTvQuizCount;
    private LottieAnimationView progressBar;
    // Reference of the quiz filter list layout
    private RadioGroup mRGHomeQuizListFilter;
    //////////////
    boolean mTwiceClicked = false;
    Snackbar mSnackbar;

    boolean mIsFilterMenuOpen = false;
    private View mDimBackground;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Injecting presenter reference
        PresenterInjector.injectHomePresenter(this);

        initializeUI();

        mPresenter.start(getIntent().getExtras());

        displaySplashScreen();

        setUpSwipeRefresh();
    }

    private void initializeUI() {
        Toolbar toolbar = findViewById(R.id.toolbarrnav);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(getDrawable(R.mipmap.ic_launcher));
        }

        RecyclerView mQuizRecyclerView = findViewById(R.id.recyclerview_quizzes);
        mQuizRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mQuizRecyclerView.setLayoutManager(linearLayoutManager);

        mQuizAdapter = new QuizAdapter(this);


        initQuizFilter();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(this);

        mDimBackground = findViewById(R.id.scrim_bg_quiz_list);

        mTvQuizCount = findViewById(R.id.total_quizzes_count);


        splashLayout = findViewById(R.id.layout_splash_screen);
        homeLayout = findViewById(R.id.homeactivitycoordinator);

        progressBar = findViewById(R.id.home_screen_pb);

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
                if (mIsFilterMenuOpen) {
                    toggleQuizFilterView(false);
                }
                break;
            case R.id.logout:
                // TODO: Show a confirmation {@link AlertDialog} here. When user cliks OK. call
                // TODO: mPresenter.logout();
                break;
            case R.id.settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadQuizzes(List<Quiz> quizzes) {
        mQuizAdapter.loadQuizzes(quizzes);
        mTvQuizCount.setText(String.valueOf(quizzes.size()));
    }

    @Override
    public void onQuizLoadError() {
        // TODO: show an alert or toast saying "quiz can't be loaded at the moment, check network connection and try again"
    }

    @Override
    public void navigateToQuizDesc(Quiz quiz) {
        // TODO: Navigate to QuizDescription Activity, use the quiz object above to extract quiz
        // TODO: details etc and pass as intent parameter.
        Intent quizDetailsIntent = new Intent(this, QuizDetailsActivity.class);
        quizDetailsIntent.putExtra(QuizDetailsContract.KEY_QUIZ_ID, quiz.getKey());
        startActivity(quizDetailsIntent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.anim_nothing);
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
        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        startActivity(notificationIntent);
    }

    @Override
    public void navigateToResources() {
        // TODO: Navigate to Resources screen / tab
    }

    @Override
    public void navigateToSettings() {
    }

    @Override
    public void navigateToAboutScreen() {
        // TODO: Navigate to About screen
    }

    @Override
    public void navigateToEditProfile() {

    }


    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        // TODO: Show progress bar / dialog here
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        // TODO: Hide progress bar / dialog here

        recyclerViewLoading = AnimationUtils.loadAnimation(this, R.anim.anim_nothing);
        quizRecyclerView.startAnimation(recyclerViewLoading);


        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onQuizClicked(Quiz quiz) {
        if (mIsFilterMenuOpen) {
            toggleQuizFilterView(false);
            mIsFilterMenuOpen = false;
        } else {
            mPresenter.onQuizClicked(quiz);
        }
    }

    @Override
    public void onBookmarkStatusChanged(Quiz quiz) {

    }

    /**
     * Initializes quiz filter
     */
    private void initQuizFilter() {
        //Set quiz filter button behavior
        (findViewById(R.id.quiz_filter_button)).setOnClickListener(view -> {
            if (mRGHomeQuizListFilter.getVisibility() == View.GONE) {
                toggleQuizFilterView(true);
            } else
                toggleQuizFilterView(false);
        });

        //Get reference to the quiz list filter layout and radio buttons
        mRGHomeQuizListFilter = findViewById(R.id.home_quiz_list_filter_radio_group);
        //Set radio group checked change listener so we can perform an action when a different
        //quiz filter is selected.
        mRGHomeQuizListFilter.setOnCheckedChangeListener(
                this::onQuizFilterItemCheckedChanged);
    }

    /**
     * Hides or shows the quiz filter view
     *
     * @param show if true, will show the view else will hide it.
     */
    private void toggleQuizFilterView(boolean show) {
        Animation bgFadingAnimation;
        if (show) {
            mRGHomeQuizListFilter.setTranslationY(TRANSLATE_Y_POSITION_BEFORE_SLIDE_DOWN);
            mRGHomeQuizListFilter.setVisibility(View.VISIBLE);
            mRGHomeQuizListFilter.animate()
                    .setInterpolator(new FastOutSlowInInterpolator())
                    .setDuration(SLIDE_DOWN_ANIMATION_DURATION)
                    .translationY(ANIMATION_SLIDE_DOWN_TRANSLATE_Y);

            mDimBackground.setVisibility(View.VISIBLE);
            bgFadingAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
            bgFadingAnimation.setDuration(1000);
            mDimBackground.startAnimation(bgFadingAnimation);
            mIsFilterMenuOpen = true;
        } else {
            mRGHomeQuizListFilter.animate()
                    .setInterpolator(new FastOutLinearInInterpolator())
                    .setDuration(QUIZ_FILTER_VIEW_SLIDE_UP_ANIMATION_DURATION)
                    .translationY(QUIZ_FILTER_VIEW_ANIMATION_SLIDE_UP_TRANSLATE_Y)
                    .withEndAction(new Runnable() {
                                       @Override
                                       public void run() {
                                           mHomeQuizListFilterRadioGroup.setVisibility(View.GONE);
                                       }
                                   }
                    );
                    .setDuration(SLIDE_UP_ANIMATION_DURATION)
                    .translationY(ANIMATION_SLIDE_UP_TRANSLATE_Y)
                    .withEndAction(() -> mRGHomeQuizListFilter.setVisibility(View.GONE)
                    );


            mDimBackground.setVisibility(View.VISIBLE);
            bgFadingAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
            mDimBackground.startAnimation(bgFadingAnimation);
            mDimBackground.setVisibility(View.GONE);
            mIsFilterMenuOpen = false;
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toggleQuizFilterView(false);
            }
        }, QUIZ_FILTER_VIEW_SLIDE_UP_DELAY_ON_CHECKED_CHANGED);

        //Perform action based on selected quiz filter
        switch (id) {
            case R.id.radio_quiz_filter_all:
                break;
            case R.id.radio_quiz_filter_attempted:
                break;
            case R.id.radio_quiz_filter_un_attempted:
                break;
            case R.id.radio_quiz_filter_bookmarks:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        final Handler mDrawerHandler = new Handler();

        mDrawerHandler.postDelayed(() -> {

            switch (item.getItemId()) {
                case R.id.scoreboard:
                    mPresenter.onNavigationItemSelected(HomeContract.NAVIGATION_SCOREBOARD);
                    break;
//                    case R.id.create_quizzes:
//                        mPresenter.onNavigationItemSelected(HomeContract.NAVIGATION_CREATE_QUIZ);
//                        break;
                case R.id.notifications:
                    mPresenter.onNavigationItemSelected(HomeContract.NAVIGATION_NOTIFICATIONS);
                    break;
                case R.id.resources:
                    mPresenter.onNavigationItemSelected(HomeContract.NAVIGATION_RESOURCES);
                    break;
                default:
                    break;
            }

        }, DELAY_NAV_ITEM_CLICK);

        mDrawerLayout.closeDrawers();

        return false;
    }

    private void displaySplashScreen(){
        homeLayout.setVisibility(View.GONE);
        splashLayout.setVisibility(View.VISIBLE);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        splashScreenProgress = findViewById(R.id.progressbar_splash);
        splashScreenProgress.setVisibility(View.VISIBLE);


        HomeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                splashProgressLoading = ValueAnimator.ofInt(0, splashScreenProgress.getMax());
                splashProgressLoading.setDuration(1500);
                splashProgressLoading.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        splashScreenProgress.setProgress((Integer) valueAnimator.getAnimatedValue());
                    }
                });
                splashProgressLoading.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                            circularReveal(R.id.homeactivitycoordinator);

                    }
                });
                splashProgressLoading.start();
            }
        });

    }

    public void circularReveal(int id) {
        //Applying the circular reveal effect on the activity.
        final View rootLayout = findViewById(id);
        rootLayout.setVisibility(View.INVISIBLE);


        ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                    int centerX = (rootLayout.getLeft() + rootLayout.getRight()) / 2;
                    int centerY = (rootLayout.getTop() + rootLayout.getBottom()) / 2;

                    int startRadius = 0;
                    int endRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

                    Animator animator = ViewAnimationUtils.createCircularReveal(rootLayout, centerX, centerY, startRadius, endRadius);
                    animator.setDuration(1000);
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            if (id == R.id.homeactivitycoordinator) {
                                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                                splashLayout.setVisibility(View.GONE);

                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    rootLayout.setVisibility(View.VISIBLE);
                    animator.start();
                }
            });
        }


    }

    SwipeRefreshLayout swipeRefreshLayout;

    private void setUpSwipeRefresh() {
        swipeRefreshLayout = findViewById(R.id.refresh_homescreen);
        swipeRefreshLayout.setColorSchemeResources(R.color.bnv_color, R.color.blue_jeans,
                R.color.ufo_green, R.color.vivid_tangelo);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(mQuizAdapter != null){
                            mQuizAdapter.notifyDataSetChanged();
                           showSnackBar(R.string.refreshed);
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, BACK_PRESS_DURATION);


            }
        });
    }

    private void showSnackBar(int string){
        String msg = getResources().getString(string);
        mSnackbar = Snackbar.make(findViewById(R.id.homeactivitycoordinator), msg, Snackbar.LENGTH_LONG);
        final View snackbarView = mSnackbar.getView();
        TextView tvSnackbar = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        tvSnackbar.setTextColor(getResources().getColor(R.color.colorAccent));
        snackbarView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
        mSnackbar.show();

        snackbarView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                snackbarView.getViewTreeObserver().removeOnPreDrawListener(this);
                ((CoordinatorLayout.LayoutParams) snackbarView.getLayoutParams()).setBehavior(null);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (mIsFilterMenuOpen) {
            toggleQuizFilterView(false);
        } else {
            if (mTwiceClicked) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                mSnackbar.dismiss();

            } else {

                mTwiceClicked = true;


               showSnackBar(R.string.home_back_btn_msg);


                new Handler().postDelayed(() -> mTwiceClicked = false, BACK_PRESS_DURATION);
            }

        }

    }
}
