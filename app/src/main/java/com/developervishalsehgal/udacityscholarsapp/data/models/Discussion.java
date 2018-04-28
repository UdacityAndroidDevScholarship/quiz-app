
package com.developervishalsehgal.udacityscholarsapp.data.models;

<<<<<<< HEAD
=======
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
>>>>>>> upstream/development
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model class representing a Quiz Discussion
 */
<<<<<<< HEAD
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
=======
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
>>>>>>> upstream/development

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
<<<<<<< HEAD
=======

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }
>>>>>>> upstream/development
}
