
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Model class representing a quiz question
 */
@IgnoreExtraProperties
public class Question {

    @Expose
    @PropertyName("description")
    @SerializedName("description")
    String mDescription;

    @Expose
    @PropertyName("marks")
    @SerializedName("marks")
    long mMarks;

    @Expose
    @PropertyName("options")
    @SerializedName("options")
    Map<String, Option> mOptions;

    @Expose
    @PropertyName("type")
    @SerializedName("type")
    String mType;

    @Expose
    @PropertyName("files")
    @SerializedName("files")
    Map<String, String> mFiles;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    String mKey;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public long getMarks() {
        return mMarks;
    }

    public void setMarks(Long marks) {
        mMarks = marks;
    }

    public Map<String, Option> getOptions() {
        return mOptions;
    }

    public void setOptions(Map<String, Option> options) {
        mOptions = options;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public Map<String, String> getFiles() {
        return mFiles;
    }

    public void setFiles(Map<String, String> files) {
        this.mFiles = files;
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
        Question question = (Question) o;
        return mMarks == question.mMarks &&
                Objects.equals(mDescription, question.mDescription) &&
                Objects.equals(mOptions, question.mOptions) &&
                Objects.equals(mType, question.mType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mDescription, mMarks, mOptions, mType);
    }

    /**
     * Clears all the isCorrect flag from options of this question.
     */
    public void resetOptions() {
        for(Option optionEntry : mOptions.values()){
            optionEntry.setIsCorrect(false);
        }
    }
}
