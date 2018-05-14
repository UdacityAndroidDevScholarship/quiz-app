
package com.developervishalsehgal.udacityscholarsapp.data.models;

import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Model class representing a quiz's options
 */
@IgnoreExtraProperties
public class Option {

    @Expose
    @SerializedName("description")
    private String mDescription;

    /**
     * Since we are using the same model to store correct answers and scholar's answers, this field
     * can represent either
     */
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
    String mKey;

    public Option() {

    }

    /**
     * Copy constructor
     *
     * @param toClone Option object to be shallow copied
     */
    public Option(@NonNull Option toClone) {
        mDescription = toClone.mDescription;
        mIsCorrect = toClone.mIsCorrect;
        mRemarks = toClone.mRemarks;
    }

    @PropertyName("description")
    public String getDescription() {
        return mDescription;
    }

    @PropertyName("description")
    public void setDescription(String description) {
        mDescription = description;
    }

    @PropertyName("is-correct")
    public boolean isCorrect() {
        return mIsCorrect;
    }

    @PropertyName("is-correct")
    public void setIsCorrect(boolean isCorrect) {
        mIsCorrect = isCorrect;
    }

    @PropertyName("remarks")
    public String getRemarks() {
        return mRemarks;
    }

    @PropertyName("remarks")
    public void setRemarks(String remarks) {
        mRemarks = remarks;
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
        Option option = (Option) o;
        return mIsCorrect == option.mIsCorrect &&
                Objects.equals(mDescription, option.mDescription) &&
                Objects.equals(mRemarks, option.mRemarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mDescription, mIsCorrect, mRemarks);
    }
}
