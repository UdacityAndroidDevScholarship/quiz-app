package com.developervishalsehgal.udacityscholarsapp.BaseActivity;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.developervishalsehgal.udacityscholarsapp.R;


/**
 * Created by developervishal on 03/04/18.
 */

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public void circularReveal(final int id) {
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
//                                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                                RelativeLayout relativeLayout = findViewById(R.id.activity_splash_screen);
                                relativeLayout.setVisibility(View.GONE);

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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
