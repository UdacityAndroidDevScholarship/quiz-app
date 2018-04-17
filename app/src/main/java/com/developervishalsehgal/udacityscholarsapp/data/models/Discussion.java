
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model class representing a Quiz Discussion
 */
public class Discussion {

    @SerializedName("deadline")
    private String mDeadline;

    @SerializedName("quiz-id")
    private String mQuizId;

    @SerializedName("quiz-title")
    private String mQuizTitle;

    @SerializedName("comments")
    private List<Comment> mComments;

    public String getDeadline() {
        return mDeadline;
    }

    public void setDeadline(String deadline) {
        mDeadline = deadline;
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

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        this.mComments = comments;
    }
}
