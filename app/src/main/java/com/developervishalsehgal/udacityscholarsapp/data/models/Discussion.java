
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
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
    String mKey;

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

    @PropertyName("comments")
    public List<Comment> getComments() {
        return mComments;
    }

    @PropertyName("comments")
    public void setComments(List<Comment> comments) {
        this.mComments = comments;
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
        Discussion that = (Discussion) o;
        return Objects.equals(mQuizId, that.mQuizId) &&
                Objects.equals(mQuizTitle, that.mQuizTitle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mQuizId, mQuizTitle);
    }
}
