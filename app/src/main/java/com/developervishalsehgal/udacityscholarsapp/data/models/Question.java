
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model class representing a quiz question
 */
public class Question {

    @SerializedName("description")
    private String mDescription;

    @SerializedName("marks")
    private Long mMarks;

    @SerializedName("options")
    private List<Option> mOptions;

    @SerializedName("type")
    private String mType;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getMarks() {
        return mMarks;
    }

    public void setMarks(Long marks) {
        mMarks = marks;
    }

    public List<Option> getOptions() {
        return mOptions;
    }

    public void setOptions(List<Option> options) {
        mOptions = options;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
