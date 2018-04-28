
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
 * Model class representing a quiz question
 */
<<<<<<< HEAD
public class Question {

    @SerializedName("description")
    private String mDescription;

    @SerializedName("marks")
    private Long mMarks;

    @SerializedName("options")
    private List<Option> mOptions;

    @SerializedName("type")
    private String mType;

=======
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
    private List<Option> mOptions;

    @Expose
    @SerializedName("type")
    private String mType;

    @Expose
    @SerializedName("files")
    private String mFiles;

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
    public Long getMarks() {
=======
    public long getMarks() {
>>>>>>> upstream/development
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

<<<<<<< HEAD
=======
    public String getFiles() {
        return mFiles;
    }

    public void setFiles(String files) {
        this.mFiles = files;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

>>>>>>> upstream/development
}
