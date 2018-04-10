package com.developervishalsehgal.udacityscholarsapp.Models;

/**
 * Created by developervishal on 31/03/18.
 */

public class Quizzes {

    private String quizname;
    private String moderator;
    private String quizlink;
    private String deadline;
    private String description;
    private String thumbnail;
    private String expired;
    private String serial;

    public Quizzes(String quizname, String moderator, String quizlink, String deadline, String description, String thumbnail, String expired, String serial) {
        this.quizname = quizname;
        this.moderator = moderator;
        this.quizlink = quizlink;
        this.deadline = deadline;
        this.description = description;
        this.thumbnail = thumbnail;
        this.expired = expired;
        this.serial = serial;
    }

    public Quizzes(String serial){
        this.serial = serial;
    }

    public String getSerial() {

        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public Quizzes(String quizname, String moderator, String quizlink, String deadline, String description, String thumbnail, String expired) {
        this.quizname = quizname;
        this.moderator = moderator;
        this.quizlink = quizlink;
        this.deadline = deadline;
        this.description = description;
        this.thumbnail = thumbnail;
        this.expired = expired;
    }

    public Quizzes(){

    }

    public Quizzes(String quizname, String moderator, String quizlink, String deadline, String description, String thumbnail) {
        this.quizname = quizname;
        this.moderator = moderator;
        this.quizlink = quizlink;
        this.deadline = deadline;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public String getQuizname() {
        return quizname;
    }

    public void setQuizname(String quizname) {
        this.quizname = quizname;
    }

    public String getModerator() {
        return moderator;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }

    public String getQuizlink() {
        return quizlink;
    }

    public void setQuizlink(String quizlink) {
        this.quizlink = quizlink;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
