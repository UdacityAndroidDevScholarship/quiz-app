
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model class representing a quiz
 */
public class Quiz {

    @SerializedName("creator-id")
    private String mCreatorId;

    @SerializedName("creator-name")
    private String mCreatorName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("difficulty")
    private String mDifficulty;

    @SerializedName("files")
    private List<String> mFiles;

    @SerializedName("last-modified")
    private String mLastModified;

    @SerializedName("lesson")
    private int mLesson;

    @SerializedName("max-marks")
    private int mMaxMarks;

    @SerializedName("questions")
    private List<Question> mQuestions;

    @SerializedName("rated-by")
    private int mRatedBy;

    @SerializedName("rating")
    private Double mRating;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("deadline")
    private String mDeadline;

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

    public Double getRating() {
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

}
