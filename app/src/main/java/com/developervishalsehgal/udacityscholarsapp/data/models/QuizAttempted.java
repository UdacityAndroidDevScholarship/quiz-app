
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Model class representing a quiz attempted by a user
 */
@IgnoreExtraProperties
public class QuizAttempted {

    @Expose
    @PropertyName("lesson")
    @SerializedName("lesson")
    long mLesson;

    @Expose
    @PropertyName("max-marks")
    @SerializedName("max-marks")
    long mMaxMarks;

    @Expose
    @PropertyName("percentage")
    @SerializedName("percentage")
    long mPercentage;

    @Expose
    @PropertyName("quiz-id")
    @SerializedName("quiz-id")
    String mQuizId;

    @Expose
    @PropertyName("quiz-title")
    @SerializedName("quiz-title")
    String mQuizTitle;

    @Expose
    @PropertyName("remarks")
    @SerializedName("remarks")
    String mRemarks;

    @Expose
    @PropertyName("score")
    @SerializedName("score")
    long mScore;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    String mKey;

    public long getLesson() {
        return mLesson;
    }

    public void setLesson(long lesson) {
        mLesson = lesson;
    }

    public long getMaxMarks() {
        return mMaxMarks;
    }

    public void setMaxMarks(long maxMarks) {
        mMaxMarks = maxMarks;
    }

    public long getPercentage() {
        return mPercentage;
    }

    public void setPercentage(long percentage) {
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

    public long getScore() {
        return mScore;
    }

    public void setScore(long score) {
        mScore = score;
    }

    public String getKey() {
        return mKey;
    }

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
