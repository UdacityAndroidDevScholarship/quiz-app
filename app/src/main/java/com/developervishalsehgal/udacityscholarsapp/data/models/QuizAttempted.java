
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a quiz attempted by a user
 */
public class QuizAttempted {

    @SerializedName("lesson")
    private Long mLesson;

    @SerializedName("max-marks")
    private Long mMaxMarks;

    @SerializedName("percentage")
    private Long mPercentage;

    @SerializedName("quiz-id")
    private String mQuizId;

    @SerializedName("quiz-title")
    private String mQuizTitle;

    @SerializedName("remarks")
    private String mRemarks;

    @SerializedName("score")
    private Long mScore;

    public Long getLesson() {
        return mLesson;
    }

    public void setLesson(Long lesson) {
        mLesson = lesson;
    }

    public Long getMaxMarks() {
        return mMaxMarks;
    }

    public void setMaxMarks(Long maxMarks) {
        mMaxMarks = maxMarks;
    }

    public Long getPercentage() {
        return mPercentage;
    }

    public void setPercentage(Long percentage) {
        mPercentage = percentage;
    }

    public String getQuizId() {
        return mQuizId;
    }

    public void setQuizId(String quizId) {
        mQuizId = quizId;
    }

    public String getQuizTitle() {
        return mQuizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        mQuizTitle = quizTitle;
    }

    public String getRemarks() {
        return mRemarks;
    }

    public void setRemarks(String remarks) {
        mRemarks = remarks;
    }

    public Long getScore() {
        return mScore;
    }

    public void setScore(Long score) {
        mScore = score;
    }

}
