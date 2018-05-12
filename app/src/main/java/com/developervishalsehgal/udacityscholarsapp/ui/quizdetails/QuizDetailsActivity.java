package com.developervishalsehgal.udacityscholarsapp.ui.quizdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;
import com.developervishalsehgal.udacityscholarsapp.ui.discussion.QuizDiscussionActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.quizattempt.AttemptQuizActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.quizattempt.AttemptQuizContract;

import java.util.Locale;

import static com.developervishalsehgal.udacityscholarsapp.ui.discussion.QuizDiscussionContract.KEY_QUIZ_ID;

public class QuizDetailsActivity extends AppCompatActivity implements QuizDetailsContract.View,
        View.OnClickListener {

    private QuizDetailsContract.Presenter mPresenter;

    // UI Elements
    TextView mTvQuizTitle;
    TextView mTvQuizAuthor;
    TextView mTvQuizReleasedOn;
    TextView mTvQuizDeadline;
    TextView mTvQuizDescription;
    TextView mTvQuizAttemptedStatus;
    FloatingActionButton mFabStart;

    ImageButton mBtnHelp;
    // UI Element ends

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details);

        // Injecting Presenter here
        PresenterInjector.injectQuizDetailsPresenter(this);

        initializaUI();

        mPresenter.start(getIntent().getExtras());
    }

    private void initializaUI() {
        mTvQuizTitle = findViewById(R.id.quiz_details_label_quiz);
        mTvQuizAuthor = findViewById(R.id.quiz_details_label_author);
        mTvQuizReleasedOn = findViewById(R.id.quiz_details_label_released);
        mTvQuizDeadline = findViewById(R.id.quiz_details_label_deadline);
        mTvQuizDescription = findViewById(R.id.quiz_details_label_about);
        mTvQuizAttemptedStatus = findViewById(R.id.quiz_details_label_status);

        mFabStart = findViewById(R.id.quiz_details_fab_start);
        mFabStart.setOnClickListener(this);

        mBtnHelp = findViewById(R.id.quiz_details_btn_help);
        mBtnHelp.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start(getIntent().getExtras());
    }

    @Override
    public void enableStartButton() {
        mFabStart.setEnabled(true);
        mFabStart.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTitle(String quizTitle) {
        mTvQuizTitle.setText(quizTitle);
    }

    @Override
    public void showAuthor(String quizAuthor) {
        mTvQuizAuthor.setText(quizAuthor);
    }

    @Override
    public void showReleaseDate(String releaseDate) {
        mTvQuizReleasedOn.setText(releaseDate);
    }

    @Override
    public void showDeadline(String quizDeadline) {
        if (quizDeadline != null && !quizDeadline.trim().isEmpty()) {
            mTvQuizDeadline.setText(quizDeadline);
        } else {
            mTvQuizDeadline.setText(R.string.txt_deadline_none);
        }
    }

    @Override
    public void showDescription(String quizDescription) {
        mTvQuizDescription.setText(quizDescription);
    }

    @Override
    public void showUserScore(String score, String maxMarks) {
        mTvQuizAttemptedStatus.setText(String.format(Locale.getDefault(), "%s / %s", score, maxMarks));
    }

    @Override
    public void navigateToDiscussion(String quizId) {
        Intent quizDiscussionIntent = new Intent(this, AttemptQuizActivity.class);
        quizDiscussionIntent.putExtra(KEY_QUIZ_ID, quizId);
        startActivity(quizDiscussionIntent);
    }

    @Override
    public void startQuiz(String quizId) {
        Intent quizDiscussionIntent = new Intent(this, QuizDiscussionActivity.class);
        quizDiscussionIntent.putExtra(AttemptQuizContract.KEY_QUIZ_ID, quizId);
        startActivity(quizDiscussionIntent);
    }

    @Override
    public void showInvalidInput() {
        Toast.makeText(this, getString(R.string.invalid_input_provided),
                Toast.LENGTH_SHORT).show();
        dismissView();
    }

    @Override
    public void onError() {
        Toast.makeText(this, getString(R.string.something_went_wrong),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissView() {
        QuizDetailsActivity.this.finish();
        QuizDetailsActivity.this.overridePendingTransition(R.anim.slide_out_down, R.anim.anim_nothing);
    }

    @Override
    public void setPresenter(QuizDetailsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quiz_details_fab_start:
                if (v.getVisibility() == View.VISIBLE) {
                    mPresenter.startQuizClicked();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.dismissView();
                break;
            case R.id.quiz_details_btn_help:
                mPresenter.onDiscussionClicked();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
