
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Model class representing a quiz
 */
@IgnoreExtraProperties
public class Quiz {

    @Expose
    @PropertyName("creator-id")
    @SerializedName("creator-id")
    String mCreatorId;

    @Expose
    @PropertyName("creator-name")
    @SerializedName("creator-name")
    String mCreatorName;

    @Expose
    @PropertyName("description")
    @SerializedName("description")
    String mDescription;

    @Expose
    @PropertyName("difficulty")
    @SerializedName("difficulty")
    String mDifficulty;

    @Expose
    @PropertyName("files")
    @SerializedName("files")
    Map<String, String> mFiles;

    @Expose
    @PropertyName("last-modified")
    @SerializedName("last-modified")
    String mLastModified;

    @Expose
    @PropertyName("lesson")
    @SerializedName("lesson")
    int mLesson;

    @Expose
    @PropertyName("max-marks")
    @SerializedName("max-marks")
    int mMaxMarks;

    @Expose
    @PropertyName("questions")
    @SerializedName("questions")
    Map<String, Question> mQuestions;

    @Expose
    @PropertyName("rated-by")
    @SerializedName("rated-by")
    int mRatedBy;

    @Expose
    @PropertyName("rating")
    @SerializedName("rating")
    double mRating;

    @Expose
    @PropertyName("title")
    @SerializedName("title")
    String mTitle;

    @Expose
    @PropertyName("deadline")
    @SerializedName("deadline")
    String mDeadline;

    /**
     * For local usage only, it is not stored in database
     */
    @Exclude
    boolean mAttempted;

    /**
     * For local usage only, it is not stored in database
     */
    @Exclude
    boolean mIsBookmarked;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    String mKey;

    public Quiz() {
    }

    /**
     * Copy constructor
     *
     * @param toClone Quiz object to be shallow copied
     */
    public Quiz(Quiz toClone) {
        mCreatorId = toClone.mCreatorId;
        mCreatorName = toClone.mCreatorName;
        mDescription = toClone.mDescription;
        mDifficulty = toClone.mDifficulty;
        mLastModified = toClone.mLastModified;
        mLesson = toClone.mLesson;
        mMaxMarks = toClone.mMaxMarks;
        mRatedBy = toClone.mRatedBy;
        mRating = toClone.mRating;
        mTitle = toClone.mTitle;
        mDeadline = toClone.mDeadline;
        mAttempted = toClone.mAttempted;
        mIsBookmarked = toClone.mIsBookmarked;

        if (toClone.mFiles != null) {
            Map<String, String> files = new HashMap<>();
            for (Map.Entry<String, String> fileEntry : toClone.mFiles.entrySet()) {
                files.put(fileEntry.getKey(), fileEntry.getValue());
            }
            mFiles = files;
        }

        if (toClone.mQuestions != null) {
            Map<String, Question> questions = new HashMap<>();
            for (Map.Entry<String, Question> questionEntry : toClone.mQuestions.entrySet()) {
                questions.put(questionEntry.getKey(), new Question(questionEntry.getValue()));
            }
            mQuestions = questions;
        }

    }

    @Exclude
    public String getCreatorId() {
        return mCreatorId;
    }

    @Exclude
    public void setCreatorId(String creatorId) {
        mCreatorId = creatorId;
    }

    @Exclude
    public String getCreatorName() {
        return mCreatorName;
    }

    @Exclude
    public void setCreatorName(String creatorName) {
        mCreatorName = creatorName;
    }

    @Exclude
    public String getDescription() {
        return mDescription;
    }

    @Exclude
    public void setDescription(String description) {
        mDescription = description;
    }

    @Exclude
    public String getDifficulty() {
        return mDifficulty;
    }

    @Exclude
    public void setDifficulty(String difficulty) {
        mDifficulty = difficulty;
    }

    @Exclude
    public Map<String, String> getFiles() {
        return mFiles;
    }

    @Exclude
    public void setFiles(Map<String, String> files) {
        mFiles = files;
    }

    @Exclude
    public String getLastModified() {
        return mLastModified;
    }

    @Exclude
    public void setLastModified(String lastModified) {
        mLastModified = lastModified;
    }

    @Exclude
    public int getLesson() {
        return mLesson;
    }

    @Exclude
    public void setLesson(int lesson) {
        mLesson = lesson;
    }

    @Exclude
    public int getMaxMarks() {
        return mMaxMarks;
    }

    @Exclude
    public void setMaxMarks(int maxMarks) {
        mMaxMarks = maxMarks;
    }

    @Exclude
    public Map<String, Question> getQuestions() {
        return mQuestions;
    }

    @Exclude
    public void setQuestions(Map<String, Question> questions) {
        mQuestions = questions;
    }

    @Exclude
    public int getRatedBy() {
        return mRatedBy;
    }

    @Exclude
    public void setRatedBy(int ratedBy) {
        mRatedBy = ratedBy;
    }

    @Exclude
    public double getRating() {
        return mRating;
    }

    @Exclude
    public void setRating(Double rating) {
        mRating = rating;
    }

    @Exclude
    public String getTitle() {
        return mTitle;
    }

    @Exclude
    public void setTitle(String title) {
        mTitle = title;
    }

    @Exclude
    public String getDeadline() {
        return mDeadline;
    }

    @Exclude
    public void setDeadline(String deadline) {
        mDeadline = deadline;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        this.mKey = key;
    }

    @Exclude
    public boolean isAttempted() {
        return mAttempted;
    }

    @Exclude
    public void setAttempted(boolean attempted) {
        this.mAttempted = attempted;
    }

    @Exclude
    public boolean isBookmarked() {
        return mIsBookmarked;
    }

    @Exclude
    public void setBookmarked(boolean bookmarked) {
        mIsBookmarked = bookmarked;
    }

    /**
     * All the questions, attempts, correct answers etc. Should be used carefully. We will be using
     * the same model to store question and scholar's answer
     */
    public void reset() {
        for (Map.Entry<String, Question> questionEntry : mQuestions.entrySet()) {
            Map<String, Option> options = questionEntry.getValue().getOptions();
            for (Map.Entry<String, Option> optionEntry : options.entrySet()) {
                optionEntry.getValue().setIsCorrect(false);
            }
        }
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
