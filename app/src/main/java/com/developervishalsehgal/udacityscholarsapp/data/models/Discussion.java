
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
    @PropertyName("quiz-id")
    @SerializedName("quiz-id")
    String mQuizId;

    @Expose
    @PropertyName("quiz-title")
    @SerializedName("quiz-title")
    String mQuizTitle;

    @Expose
    @PropertyName("comments")
    @SerializedName("comments")
    List<Comment> mComments;

    @Expose
    @PropertyName("slack-handle")
    @SerializedName("slack-handle")
    String mSlackHandle;

    @Expose
    @PropertyName("name")
    @SerializedName("name")
    String mName;

    public String getmSlackHandle() {
        return mSlackHandle;
    }

    public void setmSlackHandle(String mSlackHandle) {
        this.mSlackHandle = mSlackHandle;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }


    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    String mKey;

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
