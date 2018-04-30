
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

/**
 * Model class representing a Quiz Discussion
 */
@IgnoreExtraProperties
public class Discussion {

    @Expose
    @SerializedName("quiz-id")
    private String mQuizId;

    @Expose
    @SerializedName("quiz-title")
    private String mQuizTitle;

    @Expose
    @SerializedName("comments")
    private List<Comment> mComments;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

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

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        this.mComments = comments;
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
        Discussion that = (Discussion) o;
        return Objects.equals(mQuizId, that.mQuizId) &&
                Objects.equals(mQuizTitle, that.mQuizTitle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mQuizId, mQuizTitle);
    }
}
