
package com.developervishalsehgal.udacityscholarsapp.data.models;

<<<<<<< HEAD
=======
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
>>>>>>> upstream/development
import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a quiz's options
 */
<<<<<<< HEAD
public class Option {

    @SerializedName("description")
    private String mDescription;

    @SerializedName("is-correct")
    private Boolean mIsCorrect;

    @SerializedName("remarks")
    private String mRemarks;

=======
@IgnoreExtraProperties
public class Option {

    @Expose
    @SerializedName("description")
    private String mDescription;

    @Expose
    @SerializedName("is-correct")
    private boolean mIsCorrect;

    @Expose
    @SerializedName("remarks")
    private String mRemarks;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

>>>>>>> upstream/development
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

<<<<<<< HEAD
    public Boolean isCorrect() {
        return mIsCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
=======
    public boolean isCorrect() {
        return mIsCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
>>>>>>> upstream/development
        mIsCorrect = isCorrect;
    }

    public String getRemarks() {
        return mRemarks;
    }

    public void setRemarks(String remarks) {
        mRemarks = remarks;
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
