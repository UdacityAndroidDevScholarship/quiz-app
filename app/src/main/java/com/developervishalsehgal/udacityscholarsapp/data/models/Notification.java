package com.developervishalsehgal.udacityscholarsapp.data.models;

import com.developervishalsehgal.udacityscholarsapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by dell on 4/21/2018.
 */

public class Notification {

    private long mTimeStamp;
    private String mDescription;
    private String mTitle;
    private String mFrom;
    private String mType;
    private String mAction;
    private String mExtra1;
    private String mExtra2;

    //Getter Methods for Notification data
    public long getTimeStamp() {
        return mTimeStamp;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getFrom() {
        return mFrom;
    }

    public String getType() {
        return mType;
    }

    public String getAction() {
        return mAction;
    }

    public String getExtra1() {
        return mExtra1;
    }

    public String getExtra2() {
        return mExtra2;
    }

    //Setter Methods for Notification data
    public void setTimeStamp(long timestamp) {
        this.mTimeStamp = timestamp;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setFrom(String from) {
        this.mFrom = from;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public void setAction(String action) {
        this.mAction = action;
    }

    public void setExtra1(String extra1) {
        this.mExtra1 = extra1;
    }

    public void setExtra2(String extra2) {
        this.mExtra2 = extra2;
    }

    public static List<Notification> fromQuizzes(List<Quiz> quizzes, boolean isNew) {
        List<Notification> notificationList = new ArrayList<>();
        for (Quiz singleQuiz : quizzes) {
            Notification notification = new Notification();
            notification.setType(isNew ? AppConstants.NOTIFICATION_TYPE_QUIZ
                    : AppConstants.NOTIFICATION_TYPE_DEADLINE);
            notification.setTitle(singleQuiz.getTitle());
            notification.setTimeStamp(System.currentTimeMillis());
            notification.setAction(singleQuiz.getKey());
            notification.setDescription(singleQuiz.getDescription());
            notification.setFrom(singleQuiz.getCreatorName());
            notification.setExtra1(singleQuiz.getCreatorId());
            notification.setExtra2(singleQuiz.getDeadline());

            notificationList.add(notification);
        }
        return notificationList;
    }

    public static List<Notification> fromResources(List<Resource> resources) {
        List<Notification> notificationList = new ArrayList<>();
        for (Resource resource : resources) {
            Notification notification = new Notification();
            notification.setType(AppConstants.NOTIFICATION_TYPE_RESOURCES);
            notification.setTitle(resource.getTitle());
            notification.setTimeStamp(resource.getTimestamp());
            notification.setAction(resource.getUrl());
            notification.setDescription(resource.getDescription());
            notification.setFrom(resource.getUploadedBy());
            notification.setExtra1(resource.getCategory());
            notification.setExtra2(resource.getTags());

            notificationList.add(notification);
        }
        return notificationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return mTimeStamp == that.mTimeStamp &&
                Objects.equals(mDescription, that.mDescription) &&
                Objects.equals(mTitle, that.mTitle) &&
                Objects.equals(mFrom, that.mFrom) &&
                Objects.equals(mType, that.mType) &&
                Objects.equals(mAction, that.mAction) &&
                Objects.equals(mExtra1, that.mExtra1) &&
                Objects.equals(mExtra2, that.mExtra2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mTimeStamp, mDescription, mTitle, mFrom, mType, mAction, mExtra1, mExtra2);
    }
}