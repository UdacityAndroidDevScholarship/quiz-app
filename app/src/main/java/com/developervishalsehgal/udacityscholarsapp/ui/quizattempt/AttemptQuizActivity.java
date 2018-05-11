package com.developervishalsehgal.udacityscholarsapp.ui.quizattempt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Option;
import com.developervishalsehgal.udacityscholarsapp.data.models.Question;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;
import com.developervishalsehgal.udacityscholarsapp.utils.AppConstants;

import java.util.Locale;
import java.util.Map;

public class AttemptQuizActivity extends AppCompatActivity implements AttemptQuizContract.View,
        View.OnClickListener {

    private AttemptQuizContract.Presenter mPresenter;

    private Question mCurrentQuestion;

    private boolean mIsEvaluated;

    // UI Elements
    Toolbar mToolbar;

    TextView mTvQuestionDesc;

    RadioGroup mRgSingleChoice;
    LinearLayout mLLMultipleChoice;
    EditText mEtSubjective;

    ScrollView mSvQuestionOptionsHolder;
    FrameLayout mAllTypeHolder;
    private ImageView mImgNextQuestion, mImgPrevQuestion;

    TextView mTvQuestionStatus;
    // UI Element Ends

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt_quiz);
        initializeUI();

        // Injecting presenter here
        PresenterInjector.injectQuizAttemptPresenter(this);

        mPresenter.start(getIntent().getExtras());
    }

    private void initializeUI() {

        mToolbar = findViewById(R.id.toolbar_attempt_quiz);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_clear_black_24dp));
        }

        mTvQuestionDesc = findViewById(R.id.tv_question_description);

        mRgSingleChoice = findViewById(R.id.rg_single_choice_holder);
        mLLMultipleChoice = findViewById(R.id.ll_multiple_choice_holder);
        mEtSubjective = findViewById(R.id.et_subjective_holder);

        mSvQuestionOptionsHolder = findViewById(R.id.question_hierarchy_holder);
        mAllTypeHolder = findViewById(R.id.fl_all_options_type_holder);

        mImgNextQuestion = findViewById(R.id.img_quiz_attempt_next);
        mImgNextQuestion.setOnClickListener(this);
        mImgPrevQuestion = findViewById(R.id.img_quiz_attempt_previous);
        mImgPrevQuestion.setOnClickListener(this);

        mTvQuestionStatus = findViewById(R.id.tv_attemt_quiz_status);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start(getIntent().getExtras());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showQuizQuizConfirmation();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void enablePreviousButton() {
        mImgPrevQuestion.setVisibility(View.VISIBLE);
    }

    @Override
    public void disablePreviousButton() {
        mImgPrevQuestion.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSubmitButton() {
        mImgNextQuestion.setImageResource(R.drawable.ic_check_circle_24dp);
    }

    @Override
    public void showNextButton() {
        mImgNextQuestion.setImageResource(R.drawable.ic_next_circle_filled);
    }

    @Override
    public void loadQuestion(Question question) {
        this.mCurrentQuestion = question;
        populateQuestionDetails(mCurrentQuestion, null);
    }

    @Override
    public void loadQuestionForReview(Question attemptedQuestion, Question question) {
        populateQuestionDetails(attemptedQuestion, question);
    }

    @Override
    public void loadAttemptedStatusText(int currentQuestionNumber, int totalQuestions) {
        mTvQuestionStatus.setText(String.format(Locale.getDefault(), "%d of %d",
                currentQuestionNumber, totalQuestions));
    }

    @Override
    public void loadTitle(String quizTitle) {
        mToolbar.setTitle(quizTitle);
    }

    @Override
    public void loadResultSummary(int score, int total, double percentage) {
        new AlertDialog.Builder(this)
                .setMessage(String.format(Locale.getDefault(), "You've got %d out of %d. " +
                        "You have scored %.2f%%", score, total, (float) percentage))
                .setTitle(R.string.quiz_summary_msg)
                .setCancelable(false)
                .setPositiveButton(R.string.quiz_review_confirmation, (dialog, which) -> {
                    mPresenter.onReviewClicked();
                    dialog.dismiss();
                })
                .setNegativeButton(R.string.user_confirmation_cancel, (dialog, which) -> {
                    dialog.dismiss();
                    dismissView();
                })
                .create()
                .show();
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showInvalidInput() {
        Toast.makeText(this, getString(R.string.invalid_input_provided),
                Toast.LENGTH_SHORT).show();
        dismissView();
    }

    @Override
    public void showSubmitConfirmation() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.quiz_submit_confirmation_msg)
                .setTitle(R.string.quiz_submit_confirmation_title)
                .setCancelable(true)
                .setPositiveButton(R.string.user_confirmation_yes, (dialog, which) -> {
                    mPresenter.onSubmitClicked();
                    mIsEvaluated = true;
                })
                .setNegativeButton(R.string.user_confirmation_cancel, (dialog, which) -> {
                    dialog.dismiss();
                })
                .create()
                .show();
    }

    @Override
    public void dismissView() {
        AttemptQuizActivity.this.finish();
        AttemptQuizActivity.this.overridePendingTransition(R.anim.slide_out_down, R.anim.anim_nothing);
    }

    @Override
    public void setPresenter(AttemptQuizContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private void populateQuestionDetails(@NonNull Question userAttempt, @Nullable Question question) {

        mTvQuestionDesc.setText(String.format(Locale.getDefault(), "%s [%d marks]"
                , userAttempt.getDescription(), userAttempt.getMarks()));

        // Remove all subview before adding new ones
        mLLMultipleChoice.removeAllViews();
        mRgSingleChoice.removeAllViews();

        // Type of question
        String type = userAttempt.getType();

        boolean isReviewMode = (question != null);
        if (isReviewMode) {
            boolean isAttemptCorrect = userAttempt.equals(question);
            mTvQuestionDesc.setTextColor(isAttemptCorrect ? ContextCompat.getColor(this,
                    R.color.color_green_deadline) : ContextCompat.getColor(this,
                    R.color.color_red_deadline));
        }

        if (AppConstants.QUESTION_SINGLE_CHOICE.equalsIgnoreCase(type)) {
            mLLMultipleChoice.setVisibility(View.GONE);
            mEtSubjective.setVisibility(View.GONE);
            mRgSingleChoice.setVisibility(View.VISIBLE);

            Map<String, Option> options = userAttempt.getOptions();

            int index = 0;
            for (Map.Entry<String, Option> option : options.entrySet()) {
                Option singleOption = option.getValue();
                RadioButton radioButton = (RadioButton) LayoutInflater.from(this)
                        .inflate(R.layout.rb_single_choice, mAllTypeHolder, false);

                radioButton.setText(singleOption.getDescription());
                radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    // Since it is single choice question, reset everything before setting
                    if (isChecked) {
                        userAttempt.resetOptions();
                        option.getValue().setIsCorrect(true);
                    }
                });

                radioButton.setChecked(singleOption.isCorrect());

                // Review mode changes
                if (isReviewMode) {
                    radioButton.setEnabled(false);
                    Option correctOption = question.getOptions().get(option.getKey());

                    if (singleOption.isCorrect()) {
                        radioButton.setTextColor(ContextCompat.getColor(this,
                                R.color.color_red_deadline));
                    }

                    if (correctOption.isCorrect()) {
                        radioButton.setTextColor(ContextCompat.getColor(this,
                                R.color.color_green_deadline));
                    }
                }

                mRgSingleChoice.addView(radioButton, index);
                index++;
            }

        } else if (AppConstants.QUESTION_MULTIPLE_CHOICE.equalsIgnoreCase(type)) {

            mLLMultipleChoice.setVisibility(View.VISIBLE);
            mEtSubjective.setVisibility(View.GONE);
            mRgSingleChoice.setVisibility(View.GONE);

            Map<String, Option> options = userAttempt.getOptions();

            int index = 0;
            for (Map.Entry<String, Option> option : options.entrySet()) {
                Option singleOption = option.getValue();
                CheckBox checkBox = (CheckBox) LayoutInflater.from(this)
                        .inflate(R.layout.chk_multiple_choice, mAllTypeHolder, false);

                checkBox.setText(singleOption.getDescription());
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    option.getValue().setIsCorrect(isChecked);
                });

                checkBox.setChecked(singleOption.isCorrect());

                // Review mode changes
                if (isReviewMode) {
                    Option correctOption = question.getOptions().get(option.getKey());
                    checkBox.setEnabled(false);

                    if (singleOption.isCorrect()) {
                        checkBox.setTextColor(ContextCompat.getColor(this,
                                R.color.color_red_deadline));
                    }

                    if (correctOption.isCorrect()) {
                        checkBox.setTextColor(ContextCompat.getColor(this,
                                R.color.color_green_deadline));
                    }
                }

                mLLMultipleChoice.addView(checkBox, index);
                index++;
            }

        } else if (AppConstants.QUESTION_SUBJECTIVE_TYPE.equalsIgnoreCase(type)) {
            mLLMultipleChoice.setVisibility(View.GONE);
            mEtSubjective.setVisibility(View.VISIBLE);
            mRgSingleChoice.setVisibility(View.GONE);

            mEtSubjective.setText(mCurrentQuestion.getExtra());

            mEtSubjective.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    mCurrentQuestion.setExtra(mEtSubjective.getText().toString());
                }
            });
        }

        // Move scroll view to top every time question is populated
        mSvQuestionOptionsHolder.fullScroll(ScrollView.FOCUS_UP);
    }

    @Override
    public void onBackPressed() {
        if (mIsEvaluated) {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_out_down, R.anim.anim_nothing);

        } else {
            showQuizQuizConfirmation();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_quiz_attempt_next:
                mPresenter.onNextClicked();
                break;
            case R.id.img_quiz_attempt_previous:
                mPresenter.onPreviousClicked();
                break;
            default:
                break;
        }
    }

    private void showQuizQuizConfirmation() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.quiz_dismiss_confirmation_msg)
                .setTitle(R.string.quiz_dismiss_confirmation_title)
                .setCancelable(true)
                .setPositiveButton(R.string.user_confirmation_yes, (dialog, which) -> {
                    dialog.dismiss();
                    dismissView();
                })
                .setNegativeButton(R.string.user_confirmation_cancel, (dialog, which) -> {
                    dialog.dismiss();
                })
                .create()
                .show();
    }
}
