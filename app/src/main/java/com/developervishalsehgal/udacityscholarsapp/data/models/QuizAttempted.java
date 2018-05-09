
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
    @PropertyName("lesson")
    long mLesson;

    @Expose
    @PropertyName("max-marks")
    long mMaxMarks;

    @Expose
    @PropertyName("percentage")
    long mPercentage;

    @Expose
    @PropertyName("quiz-id")
    String mQuizId;

    @Expose
    @PropertyName("quiz-title")
    String mQuizTitle;

    @Expose
    @PropertyName("remarks")
    String mRemarks;

    @Expose
    @PropertyName("score")
    long mScore;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    String mKey;

    @Exclude
    public long getLesson() {
        return mLesson;
    }

    @Exclude
    public void setLesson(long lesson) {
        mLesson = lesson;
    }

    @Exclude
    public long getMaxMarks() {
        return mMaxMarks;
    }

    @Exclude
    public void setMaxMarks(long maxMarks) {
        mMaxMarks = maxMarks;
    }

    @Exclude
    public long getPercentage() {
        return mPercentage;
    }

    @Exclude
    public void setPercentage(long percentage) {
        mPercentage = percentage;
    }

    @Exclude
    public String getQuizId() {
        return mQuizId;
    }

    @Exclude
    public void setQuizId(String quizId) {
        mQuizId = quizId;
    }

    @Exclude
    public String getQuizTitle() {
        return mQuizTitle;
    }

    @Exclude
    public void setQuizTitle(String quizTitle) {
        mQuizTitle = quizTitle;
    }

    @Exclude
    public String getRemarks() {
        return mRemarks;
    }

    @Exclude
    public void setRemarks(String remarks) {
        mRemarks = remarks;
    }

    @Exclude
    public long getScore() {
        return mScore;
    }

    @Exclude
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
