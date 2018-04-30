
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

/**
 * Model class representing a quiz
 */
@IgnoreExtraProperties
public class Quiz {

    @Expose
    @SerializedName("creator-id")
    private String mCreatorId;

    @Expose
    @SerializedName("creator-name")
    private String mCreatorName;

    @Expose
    @SerializedName("description")
    private String mDescription;

    @Expose
    @SerializedName("difficulty")
    private String mDifficulty;

    @Expose
    @SerializedName("files")
    private List<String> mFiles;

    @Expose
    @SerializedName("last-modified")
    private String mLastModified;

    @Expose
    @SerializedName("lesson")
    private int mLesson;

    @Expose
    @SerializedName("max-marks")
    private int mMaxMarks;

    @Expose
    @SerializedName("questions")
    private List<Question> mQuestions;

    @Expose
    @SerializedName("rated-by")
    private int mRatedBy;

    @Expose
    @SerializedName("rating")
    private double mRating;

    @Expose
    @SerializedName("title")
    private String mTitle;

    @Expose
    @SerializedName("deadline")
    private String mDeadline;

    @Exclude
    private boolean attempted;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

    public String getCreatorId() {
        return mCreatorId;
    }

    public void setCreatorId(String creatorId) {
        mCreatorId = creatorId;
    }

    public String getCreatorName() {
        return mCreatorName;
    }

    public void setCreatorName(String creatorName) {
        mCreatorName = creatorName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDifficulty() {
        return mDifficulty;
    }

    public void setDifficulty(String difficulty) {
        mDifficulty = difficulty;
    }

    public List<String> getFiles() {
        return mFiles;
    }

    public void setFiles(List<String> files) {
        mFiles = files;
    }

    public String getLastModified() {
        return mLastModified;
    }

    public void setLastModified(String lastModified) {
        mLastModified = lastModified;
    }

    public int getLesson() {
        return mLesson;
    }

    public void setLesson(int lesson) {
        mLesson = lesson;
    }

    public int getMaxMarks() {
        return mMaxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        mMaxMarks = maxMarks;
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public void setQuestions(List<Question> questions) {
        mQuestions = questions;
    }

    public int getRatedBy() {
        return mRatedBy;
    }

    public void setRatedBy(int ratedBy) {
        mRatedBy = ratedBy;
    }

    public double getRating() {
        return mRating;
    }

    public void setRating(Double rating) {
        mRating = rating;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDeadline() {
        return mDeadline;
    }

    public void setDeadline(String deadline) {
        mDeadline = deadline;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

    public boolean isAttempted() {
        return attempted;
    }

    public void setAttempted(boolean attempted) {
        this.attempted = attempted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return mLesson == quiz.mLesson &&
                mMaxMarks == quiz.mMaxMarks &&
                mRatedBy == quiz.mRatedBy &&
                Double.compare(quiz.mRating, mRating) == 0 &&
                Objects.equals(mCreatorId, quiz.mCreatorId) &&
                Objects.equals(mCreatorName, quiz.mCreatorName) &&
                Objects.equals(mDescription, quiz.mDescription) &&
                Objects.equals(mDifficulty, quiz.mDifficulty) &&
                Objects.equals(mFiles, quiz.mFiles) &&
                Objects.equals(mLastModified, quiz.mLastModified) &&
                Objects.equals(mQuestions, quiz.mQuestions) &&
                Objects.equals(mTitle, quiz.mTitle) &&
                Objects.equals(mDeadline, quiz.mDeadline);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mCreatorId, mCreatorName, mDescription, mDifficulty, mFiles,
                mLastModified, mLesson, mMaxMarks, mQuestions, mRatedBy, mRating,
                mTitle, mDeadline);
    }
}
