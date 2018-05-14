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
    @SerializedName("description")
    private String mDescription;

    @Expose
    @SerializedName("marks")
    private long mMarks;

    @Expose
    @SerializedName("options")
    private Map<String, Option> mOptions;

    @Expose
    @SerializedName("type")
    private String mType;

    @Expose
    @SerializedName("files")
    private Map<String, String> mFiles;

    @Exclude
    private String mExtra;

    /**
     * This field should be used for storing key of realtime database snapshot, otherwise ignore it
     */
    @Exclude
    private String mKey;

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

    @PropertyName("description")
    public String getDescription() {
        return mDescription;
    }

    @PropertyName("description")
    public void setDescription(String description) {
        mDescription = description;
    }

    @PropertyName("marks")
    public long getMarks() {
        return mMarks;
    }

    @PropertyName("marks")
    public void setMarks(Long marks) {
        mMarks = marks;
    }

    @PropertyName("options")
    public Map<String, Option> getOptions() {
        return mOptions;
    }

    @PropertyName("options")
    public void setOptions(Map<String, Option> options) {
        mOptions = options;
    }

    @PropertyName("type")
    public String getType() {
        return mType;
    }

    @PropertyName("type")
    public void setType(String type) {
        mType = type;
    }

    @PropertyName("files")
    public Map<String, String> getFiles() {
        return mFiles;
    }

    @PropertyName("files")
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