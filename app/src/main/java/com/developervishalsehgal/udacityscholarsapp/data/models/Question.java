
package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
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

    @Exclude
    String mExtra;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    String mKey;

    public Question() {
    }

    /**
     * Copy constructor
     *
     * @param toClone Question object to be shallow copied
     */
    public Question(Question toClone) {
        mDescription = toClone.mDescription;
        mMarks = toClone.mMarks;
        mType = toClone.mType;
        mExtra = toClone.mExtra;
        mKey = toClone.mKey;

        if (toClone.mFiles != null) {
            Map<String, String> files = new HashMap<>();
            for (Map.Entry<String, String> fileEntry : toClone.mFiles.entrySet()) {
                files.put(fileEntry.getKey(), fileEntry.getValue());
            }
            mFiles = files;
        }

        if (toClone.mOptions != null) {
            Map<String, Option> options = new HashMap<>();
            for (Map.Entry<String, Option> optionEntry : toClone.mOptions.entrySet()) {
                options.put(optionEntry.getKey(), new Option(optionEntry.getValue()));
            }
            mOptions = options;
        }

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
    public long getMarks() {
        return mMarks;
    }

    @Exclude
    public void setMarks(Long marks) {
        mMarks = marks;
    }

    @Exclude
    public Map<String, Option> getOptions() {
        return mOptions;
    }

    @Exclude
    public void setOptions(Map<String, Option> options) {
        mOptions = options;
    }

    @Exclude
    public String getType() {
        return mType;
    }

    @Exclude
    public void setType(String type) {
        mType = type;
    }

    @Exclude
    public Map<String, String> getFiles() {
        return mFiles;
    }

    @Exclude
    public void setFiles(Map<String, String> files) {
        this.mFiles = files;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        this.mKey = key;
    }

    @Exclude
    public String getExtra() {
        return mExtra;
    }

    @Exclude
    public void setExtra(String extra) {
        this.mExtra = extra;
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
        if (mOptions != null) {
            for (Map.Entry<String, Option> optionEntry : mOptions.entrySet()) {
                optionEntry.getValue().setIsCorrect(false);
            }
        }
    }
}
