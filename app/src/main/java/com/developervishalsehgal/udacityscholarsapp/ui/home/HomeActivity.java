package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    /* static constants for quiz filter */
    private static final int QUIZ_FILTER_VIEW_SLIDE_DOWN_ANIMATION_DURATION = 600;
    private static final int QUIZ_FILTER_VIEW_SLIDE_UP_ANIMATION_DURATION = 450;
    private static final int QUIZ_FILTER_VIEW_TRANSLATE_Y_POSITION_BEFORE_SLIDE_DOWN = -1000;
    private static final int QUIZ_FILTER_VIEW_ANIMATION_SLIDE_DOWN_TRANSLATE_Y = 0;
    private static final int QUIZ_FILTER_VIEW_ANIMATION_SLIDE_UP_TRANSLATE_Y = -1000;
    private static final int QUIZ_FILTER_VIEW_SLIDE_UP_DELAY_ON_CHECKED_CHANGED = 350;

    private RecyclerView mQuizRecyclerView;
    private QuizAdapter mQuizAdapter;

    //Reference of the quiz filter list layout
    private RadioGroup mHomeQuizListFilterRadioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarrnav);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_udacity));
        }

        mQuizRecyclerView = findViewById(R.id.recyclerview_quizzes);
        mQuizRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mQuizRecyclerView.setLayoutManager(linearLayoutManager);

        mQuizAdapter = new QuizAdapter(this);

        mQuizRecyclerView.setAdapter(mQuizAdapter);

        initQuizFilter();
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
            case R.id.logout:
                //Todo: Implement logout functionality
                Toast.makeText(this,"Action logout", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadQuizzes(List<Quiz> quizzes) {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * Initializes quiz filter
     */
    private void initQuizFilter() {
        //Set quiz filter button behavior
        (findViewById(R.id.quiz_filter_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHomeQuizListFilterRadioGroup.getVisibility() == View.GONE)
                    toggleQuizFilterView(true);
                else
                    toggleQuizFilterView(false);
            }
        });

        //Get reference to the quiz list filter layout and radio buttons
        mHomeQuizListFilterRadioGroup = findViewById(R.id.home_quiz_list_filter_radio_group);
        //Set radio group checked change listener so we can perform an action when a different
        //quiz filter is selected.
        mHomeQuizListFilterRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                onQuizFilterItemCheckedChanged(radioGroup,i);
            }
        });
    }

    /**
     * Hides or shows the quiz filter view
     * @param show if true, will show the view else will hide it.
     */
    private void toggleQuizFilterView(boolean show) {
        if(show) {
            mHomeQuizListFilterRadioGroup.setTranslationY(QUIZ_FILTER_VIEW_TRANSLATE_Y_POSITION_BEFORE_SLIDE_DOWN);
            mHomeQuizListFilterRadioGroup.setVisibility(View.VISIBLE);
            mHomeQuizListFilterRadioGroup.animate()
                    .setInterpolator(new FastOutSlowInInterpolator())
                    .setDuration(QUIZ_FILTER_VIEW_SLIDE_DOWN_ANIMATION_DURATION)
                    .translationY(QUIZ_FILTER_VIEW_ANIMATION_SLIDE_DOWN_TRANSLATE_Y);
        } else {
            mHomeQuizListFilterRadioGroup.animate()
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
        }
    }

    /**
     * This method will be invoked when quiz filter option is changed.
     * @param radioGroup RadioGroup reference
     * @param id id of the radio button
     */
    private void onQuizFilterItemCheckedChanged(RadioGroup radioGroup, int id) {
        //Hide the quiz filter view after few ms
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toggleQuizFilterView(false);
            }
        },QUIZ_FILTER_VIEW_SLIDE_UP_DELAY_ON_CHECKED_CHANGED);

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
}
