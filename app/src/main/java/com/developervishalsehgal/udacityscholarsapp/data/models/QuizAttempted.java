
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a quiz attempted by a user
 */
@IgnoreExtraProperties
public class QuizAttempted {

    @Expose
    @SerializedName("lesson")
    private long mLesson;

    @Expose
    @SerializedName("max-marks")
    private long mMaxMarks;

    @Expose
    @SerializedName("percentage")
    private long mPercentage;

    @Expose
    @SerializedName("quiz-id")
    private String mQuizId;

    @Expose
    @SerializedName("quiz-title")
    private String mQuizTitle;

    @Expose
    @SerializedName("remarks")
    private String mRemarks;

    @Expose
    @SerializedName("score")
    private long mScore;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

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

}