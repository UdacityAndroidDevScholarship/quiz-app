package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
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
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;
import com.developervishalsehgal.udacityscholarsapp.ui.discussion.QuizDiscussionActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.discussion.QuizDiscussionContract;
import com.developervishalsehgal.udacityscholarsapp.ui.notification.NotificationActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.quizdetails.QuizDetailsActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.quizdetails.QuizDetailsContract;
import com.developervishalsehgal.udacityscholarsapp.ui.settings.SettingsActivity;
import com.developervishalsehgal.udacityscholarsapp.utils.AppConstants;
import com.developervishalsehgal.udacityscholarsapp.utils.Connectivity;

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
    private static final int DELAY_NAV_ITEM_CLICK = 250;

    private QuizAdapter mQuizAdapter;

    private HomeContract.Presenter mPresenter;

    private RelativeLayout splashLayout;
    private CoordinatorLayout homeLayout;
    private ProgressBar splashScreenProgress;

    private ValueAnimator splashProgressLoading;

    // UI Elements
    private DrawerLayout mDrawerLayout;
    private RecyclerView mQuizRecyclerView;

    private TextView mTvQuizCount;
    private LottieAnimationView progressBar;

    private RadioGroup mRGHomeQuizListFilter;

    private ImageView mIVEmptyFilterResult;
    private TextView mTVEmptyFilterResult;
    private LinearLayout mLLEmptyFilterResultContainer;

    boolean mTwiceClicked = false;
    Snackbar mSnackbar;

    boolean mIsFilterMenuOpen = false;
    private View mDimBackground;

    private TextView mEmptyStateTextView;

    private ImageView mImgUserPic;
    private TextView mTvUserName;
    private TextView mTvSlackHandle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Injecting presenter reference
        PresenterInjector.injectHomePresenter(this);

        initializeUI();

        if (Connectivity.isNetworkAvailable(this)) {
            mPresenter.start(getIntent().getExtras());
        } else {
            noInternetMessage();
        }

        displaySplashScreen();

        setUpSwipeRefresh();
    }

    private void initializeUI() {
        Toolbar toolbar = findViewById(R.id.toolbarrnav);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_menu_white_24dp));
        }

        mQuizRecyclerView = findViewById(R.id.recyclerview_quizzes);
        mQuizRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mQuizRecyclerView.setLayoutManager(linearLayoutManager);

        mQuizAdapter = new QuizAdapter(this);
        mQuizRecyclerView.setAdapter(mQuizAdapter);

        mIVEmptyFilterResult = findViewById(R.id.iv_empty_filter_result);
        mTVEmptyFilterResult = findViewById(R.id.tv_empty_filter_result);
        mLLEmptyFilterResultContainer = findViewById(R.id.ll_empty_filter_result_container);


        initQuizFilter();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        mDimBackground = findViewById(R.id.scrim_bg_quiz_list);

        mTvQuizCount = findViewById(R.id.total_quizzes_count);


        splashLayout = findViewById(R.id.layout_splash_screen);
        homeLayout = findViewById(R.id.homeactivitycoordinator);

        progressBar = findViewById(R.id.home_screen_pb);

        View navHeaderView = navigationView.getHeaderView(0);

        mImgUserPic = navHeaderView.findViewById(R.id.userimage_nav_drawer);
        mTvSlackHandle = navHeaderView.findViewById(R.id.slack_name_nav_drawer);
        mTvUserName = navHeaderView.findViewById(R.id.username_nav_drawer);

        findViewById(R.id.settings).setOnClickListener(v -> {
            if (mPresenter != null) {
                mPresenter.onNavigationItemSelected(HomeContract.NAVIGATION_SETTINGS);
            }
        });

        //initializing empty view
        mEmptyStateTextView = findViewById(R.id.empty_view);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }


    private void noInternetMessage() {
        mQuizRecyclerView.setVisibility(View.GONE);
        mEmptyStateTextView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

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
        if (!quizzes.isEmpty()) {
            mLLEmptyFilterResultContainer.setVisibility(View.GONE);
        }
        mQuizRecyclerView.setVisibility(View.VISIBLE);
        mEmptyStateTextView.setVisibility(View.GONE);
        mQuizAdapter.loadQuizzes(quizzes);
        mTvQuizCount.setText(String.valueOf(quizzes.size()));
    }

    @Override
    public void onQuizLoadError() {
        // TODO: show an alert or toast saying "quiz can't be loaded at the moment, check network connection and try again"
    }

    @Override
    public void loadUserImageInDrawer(String imageUrl) {
        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).into(mImgUserPic);
        }
    }

    @Override
    public void loadUserNameInDrawer(String username) {
        if (username != null) {
            mTvUserName.setText(username);
        }
    }

    @Override
    public void loadSlackHandleInDrawer(String slackHandle) {
        if (slackHandle != null) {
            mTvSlackHandle.setText(slackHandle);
        }
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
        Toast.makeText(getApplicationContext(), R.string.msg_under_construction, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToCreateQuiz() {
        // TODO: Navigate to Create Quiz screen
        Toast.makeText(getApplicationContext(), R.string.msg_under_construction, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToNotifications() {
        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        startActivity(notificationIntent);
    }

    @Override
    public void navigateToResources() {
        Intent resourcesIntent = new Intent(this, NotificationActivity.class);
        resourcesIntent.putExtra(AppConstants.NOTIFICATION_TYPE_RESOURCES, true);
        startActivity(resourcesIntent);
    }

    @Override
    public void navigateToSettings() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    @Override
    public void navigateToAboutScreen() {
        // TODO: Navigate to About screen
        Toast.makeText(getApplicationContext(), R.string.msg_under_construction, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToEditProfile() {
        // TODO: Navigate to edit profile activity
        Toast.makeText(getApplicationContext(), R.string.msg_under_construction, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToQuizDiscussion(String quizId) {
        Intent quizDiscussionIntent = new Intent(this, QuizDiscussionActivity.class);
        quizDiscussionIntent.putExtra(QuizDiscussionContract.KEY_QUIZ_ID, quizId);
        startActivity(quizDiscussionIntent);
    }

    @Override
    public void navigateToQuizDetails(String quizId) {
        Intent quizDetailsIntent = new Intent(this, QuizDiscussionActivity.class);
        quizDetailsIntent.putExtra(QuizDetailsContract.KEY_QUIZ_ID, quizId);
        startActivity(quizDetailsIntent);
    }

    @Override
    public void handleEmptyView(String selectedFilter) {
        mLLEmptyFilterResultContainer.setVisibility(View.VISIBLE);
        switch (selectedFilter) {
            case HomeContract.ATTEMPTED_QUIZZES:
                mIVEmptyFilterResult.setImageResource(R.drawable.ic_frown_face);
                mTVEmptyFilterResult.setText(R.string.no_attempted_quizzes);
                break;
            case HomeContract.BOOKMARKED_QUIZZES:
                mIVEmptyFilterResult.setImageResource(R.drawable.ic_bookmark_warning);
                mTVEmptyFilterResult.setText(R.string.no_bookmarked_quizzes);
                break;
            case HomeContract.UNATTEMPTED_QUIZZES:
                mIVEmptyFilterResult.setImageResource(R.drawable.ic_fireworks);
                mTVEmptyFilterResult.setText(R.string.no_un_attempted_quizzes);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        Animation recyclerViewLoading = AnimationUtils.loadAnimation(this, R.anim.anim_nothing);
        mQuizRecyclerView.startAnimation(recyclerViewLoading);
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
        mPresenter.onBookmarkStatusChange(quiz);
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

    private void displaySplashScreen() {
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
                splashProgressLoading.addUpdateListener(valueAnimator -> splashScreenProgress.setProgress((Integer) valueAnimator.getAnimatedValue()));
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
        swipeRefreshLayout.setOnRefreshListener(() -> {

            mPresenter.start(getIntent().getExtras());

            swipeRefreshLayout.setRefreshing(true);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (mQuizAdapter != null) {
                    mQuizAdapter.notifyDataSetChanged();
                    showSnackBar(R.string.refreshed);
                }
                swipeRefreshLayout.setRefreshing(false);
            }, BACK_PRESS_DURATION);
        });
    }

    private void showSnackBar(int string) {
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
