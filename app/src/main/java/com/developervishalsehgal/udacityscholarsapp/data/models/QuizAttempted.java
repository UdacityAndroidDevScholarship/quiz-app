
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;

import java.util.Objects;

/**
 * Model class representing a quiz attempted by a user
 */
@IgnoreExtraProperties
public class QuizAttempted {

    @Expose
    private long mLesson;

    @Expose
    private long mMaxMarks;

    @Expose
    private long mPercentage;

    @Expose
    private String mQuizId;

    @Expose
    private String mQuizTitle;

    @Expose
    private String mRemarks;

    @Expose
    private long mScore;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

    @PropertyName("lesson")
    public long getLesson() {
        return mLesson;
    }

    @PropertyName("lesson")
    public void setLesson(long lesson) {
        mLesson = lesson;
    }

    @PropertyName("max-marks")
    public long getMaxMarks() {
        return mMaxMarks;
    }

    @PropertyName("max-marks")
    public void setMaxMarks(long maxMarks) {
        mMaxMarks = maxMarks;
    }

    @PropertyName("percentage")
    public long getPercentage() {
        return mPercentage;
    }

    @PropertyName("percentage")
    public void setPercentage(long percentage) {
        mPercentage = percentage;
    }

    @PropertyName("quiz-id")
    public String getQuizId() {
        return mQuizId;
    }

    @PropertyName("quiz-id")
    public void setQuizId(String quizId) {
        mQuizId = quizId;
    }

    @PropertyName("quiz-title")
    public String getQuizTitle() {
        return mQuizTitle;
    }

    @PropertyName("quiz-title")
    public void setQuizTitle(String quizTitle) {
        mQuizTitle = quizTitle;
    }

    @PropertyName("remarks")
    public String getRemarks() {
        return mRemarks;
    }

    @PropertyName("remarks")
    public void setRemarks(String remarks) {
        mRemarks = remarks;
    }

    @PropertyName("score")
    public long getScore() {
        return mScore;
    }

    @PropertyName("score")
    public void setScore(long score) {
        mScore = score;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        this.mKey = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizAttempted that = (QuizAttempted) o;
        return mLesson == that.mLesson &&
                mMaxMarks == that.mMaxMarks &&
                mPercentage == that.mPercentage &&
                mScore == that.mScore &&
                Objects.equals(mQuizId, that.mQuizId) &&
                Objects.equals(mQuizTitle, that.mQuizTitle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mLesson, mMaxMarks, mPercentage, mQuizId, mQuizTitle, mScore);
    }
}
