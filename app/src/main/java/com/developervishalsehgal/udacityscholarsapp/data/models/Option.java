
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
    @PropertyName("description")
    @SerializedName("description")
    String mDescription;

    /**
     * Since we are using the same model to store correct answers and scholar's answers, this field
     * can represent either
     */
    @Expose
    @PropertyName("is-correct")
    @SerializedName("is-correct")
    boolean mIsCorrect;

    @Expose
    @PropertyName("remarks")
    @SerializedName("remarks")
    String mRemarks;

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

    @Exclude
    public String getDescription() {
        return mDescription;
    }

    @Exclude
    public void setDescription(String description) {
        mDescription = description;
    }

    @Exclude
    public boolean isCorrect() {
        return mIsCorrect;
    }

    @Exclude
    public void setIsCorrect(boolean isCorrect) {
        mIsCorrect = isCorrect;
    }

    @Exclude
    public String getRemarks() {
        return mRemarks;
    }

    @Exclude
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
