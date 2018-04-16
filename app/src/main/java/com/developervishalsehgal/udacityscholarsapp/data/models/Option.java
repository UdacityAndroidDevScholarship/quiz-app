
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a quiz's options
 */
public class Option {

    @SerializedName("description")
    private String mDescription;

    @SerializedName("is-correct")
    private Boolean mIsCorrect;

    @SerializedName("remarks")
    private String mRemarks;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Boolean isCorrect() {
        return mIsCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        mIsCorrect = isCorrect;
    }

    public String getRemarks() {
        return mRemarks;
    }

    public void setRemarks(String remarks) {
        mRemarks = remarks;
    }

}
