
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
    private Map<String, String> mFiles;

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
    private Map<String, Question> mQuestions;

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

    /**
     * For local usage only, it is not stored in database
     */
    @Exclude
    private boolean mAttempted;

    /**
     * For local usage only, it is not stored in database
     */
    @Exclude
    private boolean mIsBookmarked;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

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

    @PropertyName("creator-id")
    public String getCreatorId() {
        return mCreatorId;
    }

    @PropertyName("creator-id")
    public void setCreatorId(String creatorId) {
        mCreatorId = creatorId;
    }

    @PropertyName("creator-name")
    public String getCreatorName() {
        return mCreatorName;
    }

    @PropertyName("creator-name")
    public void setCreatorName(String creatorName) {
        mCreatorName = creatorName;
    }

    @PropertyName("description")
    public String getDescription() {
        return mDescription;
    }

    @PropertyName("description")
    public void setDescription(String description) {
        mDescription = description;
    }

    @PropertyName("difficulty")
    public String getDifficulty() {
        return mDifficulty;
    }

    @PropertyName("difficulty")
    public void setDifficulty(String difficulty) {
        mDifficulty = difficulty;
    }

    @PropertyName("files")
    public Map<String, String> getFiles() {
        return mFiles;
    }

    @PropertyName("files")
    public void setFiles(Map<String, String> files) {
        mFiles = files;
    }

    @PropertyName("last-modified")
    public String getLastModified() {
        return mLastModified;
    }

    @PropertyName("last-modified")
    public void setLastModified(String lastModified) {
        mLastModified = lastModified;
    }

    @PropertyName("lesson")
    public int getLesson() {
        return mLesson;
    }

    @PropertyName("lesson")
    public void setLesson(int lesson) {
        mLesson = lesson;
    }

    @PropertyName("max-marks")
    public int getMaxMarks() {
        return mMaxMarks;
    }

    @PropertyName("max-marks")
    public void setMaxMarks(int maxMarks) {
        mMaxMarks = maxMarks;
    }

    @PropertyName("questions")
    public Map<String, Question> getQuestions() {
        return mQuestions;
    }

    @PropertyName("questions")
    public void setQuestions(Map<String, Question> questions) {
        mQuestions = questions;
    }

    @PropertyName("rated-by")
    public int getRatedBy() {
        return mRatedBy;
    }

    @PropertyName("rated-by")
    public void setRatedBy(int ratedBy) {
        mRatedBy = ratedBy;
    }

    @PropertyName("rating")
    public double getRating() {
        return mRating;
    }

    @PropertyName("rating")
    public void setRating(Double rating) {
        mRating = rating;
    }

    @PropertyName("title")
    public String getTitle() {
        return mTitle;
    }

    @PropertyName("title")
    public void setTitle(String title) {
        mTitle = title;
    }

    @PropertyName("deadline")
    public String getDeadline() {
        return mDeadline;
    }

    @PropertyName("deadline")
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
