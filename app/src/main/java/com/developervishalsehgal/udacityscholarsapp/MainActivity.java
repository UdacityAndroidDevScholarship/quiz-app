package com.developervishalsehgal.udacityscholarsapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.developervishalsehgal.udacityscholarsapp.Adapters.QuizRecyclerViewAdapter;
import com.developervishalsehgal.udacityscholarsapp.BaseActivity.BaseActivity;
import com.developervishalsehgal.udacityscholarsapp.Fragments.QuizzesFragment;
import com.developervishalsehgal.udacityscholarsapp.IntroActivity.IntroActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends BaseActivity implements QuizzesFragment.OnCompleteListener,QuizzesFragment.OnCompleteLeftQuizzes {

    RelativeLayout relativeLayout;
    android.support.v7.widget.Toolbar toolbar;
    View dot1, dot2, dot3;
    AnimatorSet anim1, anim2, anim3, finalSet;
    LinearLayout loadingDots;
    private DrawerLayout drawer;
    TextView quizzes_count,left_quizzes_count, app_title;


    private Vibrator v;

    private Typeface typeface;


    private DatabaseReference mFirebaseDatabaseReference;
    static boolean calledAlready = false;


    private void initViews() {

        relativeLayout = findViewById(R.id.activity_splash_screen);
        loadingDots = findViewById(R.id.loading_dots);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbarrnav);
        quizzes_count = findViewById(R.id.total_quizzes_count);
        left_quizzes_count = findViewById(R.id.left_quizzes_count);
        app_title = findViewById(R.id.app_title);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/font_app_title.ttf");
        app_title.setTypeface(typeface);


        initProgressBarDots();
    }

    private void initProgressBarDots() {
        dot1 = findViewById(R.id.dot_one);
        dot1.setScaleX(0);
        dot1.setScaleY(0);
        dot1.setVisibility(View.VISIBLE);

        dot2 = findViewById(R.id.dot_two);
        dot2.setScaleX(0);
        dot2.setScaleY(0);
        dot2.setVisibility(View.VISIBLE);

        dot3 = findViewById(R.id.dot_three);
        dot3.setScaleX(0);
        dot3.setScaleY(0);
        dot3.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();
        this.setSupportActionBar(toolbar);

        if (getIntent().getExtras() != null) {
            relativeLayout.setVisibility(View.GONE);
            CoordinatorLayout coordinatorLayout = findViewById(R.id.homeactivitycoordinator);
            coordinatorLayout.setVisibility(View.VISIBLE);
        } else {
            displaySplashScreen();
        }

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        Drawable drawable = getResources().getDrawable(R.drawable.udacity_app_logo);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        Drawable finalDrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap,80,80,true));

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(finalDrawable);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        /**
         * Setting up the Fragment to this Activity.
         */
        QuizzesFragment homeFragment = new QuizzesFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();


//        databaseFirebase();

    }

    private void dotsAnimation(float value, boolean order) {
        Interpolator interpolator = new FastOutSlowInInterpolator();
        long duration = 375L;
        long delay = duration / 3;

        anim1 = new AnimatorSet();
        anim1.play(ObjectAnimator.ofFloat(dot1, "scaleX", value))
                .with(ObjectAnimator.ofFloat(dot1, "scaleY", value));
        anim1.setDuration(duration);
        anim1.setInterpolator(interpolator);
        anim1.setStartDelay(delay);
        anim2 = new AnimatorSet();
        anim2.play(ObjectAnimator.ofFloat(dot2, "scaleX", value))
                .with(ObjectAnimator.ofFloat(dot2, "scaleY", value));
        anim2.setDuration(duration);
        anim2.setInterpolator(interpolator);
        anim2.setStartDelay(delay * 2);
        anim3 = new AnimatorSet();
        anim3.play(ObjectAnimator.ofFloat(dot3, "scaleX", value))
                .with(ObjectAnimator.ofFloat(dot3, "scaleY", value));
        anim3.setDuration(duration);
        anim3.setInterpolator(interpolator);
        anim3.setStartDelay(delay * 3);

        finalSet = new AnimatorSet();

        finalSet.play(anim1).with(anim2).with(anim3);
        finalSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                initViews();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                dot1.setVisibility(View.INVISIBLE);
                dot2.setVisibility(View.INVISIBLE);
                dot3.setVisibility(View.INVISIBLE);
                dotsAnimation(1, true);
            }
        });
        finalSet.start();
    }


    private void displaySplashScreen() {
        relativeLayout.setVisibility(View.VISIBLE);
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        if (getAppLaunch()) {
            dotsAnimation(1, true);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    circularReveal(R.id.homeactivitycoordinator);
                    setVibrationState();
                }
            },3000);
        } else {
            launchIntroScreen();
        }

    }

    private boolean getVibrationState() {
        SharedPreferences ss = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isVibrate = ss.getBoolean("vibrate_on_start_switch", true);
        return isVibrate;
    }
    private void setVibrationState() {
        v = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (getVibrationState()) {
            // Vibrate for 50 milliseconds
            v.vibrate(50);
        } else if (!getVibrationState()) {
            v.cancel();
        }
    }

    public boolean getAppLaunch() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppLaunch", Context.MODE_PRIVATE);
        boolean bool = sharedPreferences.getBoolean("finish", false);
        return bool;
    }


    protected void launchIntroScreen() {
        startActivity(new Intent(MainActivity.this, IntroActivity.class));
    }

    // Here is the Code for the BackButton Pressed Twice to Exit.
    boolean twiceClicked = false;
    Snackbar snackbar;

    @Override
    public void onBackPressed() {

        if (relativeLayout.getVisibility() == View.VISIBLE) {

        } else {

            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                if (twiceClicked) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                    snackbar.dismiss();

                } else {

                    twiceClicked = true;


                    snackbar = Snackbar.make(findViewById(R.id.homeactivitycoordinator), "Press back again to exit.", Snackbar.LENGTH_LONG);
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

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            twiceClicked = false;
                        }
                    }, 3000);
                }

            }

        }


    }


    @Override
    public void onComplete(String totalQuizzes) {
        if(!totalQuizzes.isEmpty()){
        quizzes_count.setText(totalQuizzes);
        }

    }


    @Override
    public void onCompletes(String totalQuizzes) {
        if(!totalQuizzes.isEmpty()){
            left_quizzes_count.setText(totalQuizzes);
        }
    }
}

