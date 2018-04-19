package com.developervishalsehgal.udacityscholarsapp.data;

import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

import java.util.List;

/**
 * This is the implementation of {@link DataHandler} interface. This class should either directly
 * deal with shared preferences of via delegate class
 *
 * TODO change this description after implementation
 *
 * @Author kaushald
 */
class AppDataHandler implements DataHandler {

    @Override
    public void saveUserName(String userName) {
        // TODO save it into shared preferences
    }

    @Override
    public void saveUserEmail(String userEmail) {

    }

    @Override
    public void saveUsertrack(String userTrack) {

    }

    @Override
    public String getUserEmail() {
        return null;
    }

    @Override
    public List<Quiz> getQuizzes() {
        // should get it from firebase database
        return null; // some mock data here
    }
}
