
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isCorrect() {
        return mIsCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        mIsCorrect = isCorrect;
    }

    public String getRemarks() {
        return mRemarks;
    }

    public void setRemarks(String remarks) {
        mRemarks = remarks;
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
