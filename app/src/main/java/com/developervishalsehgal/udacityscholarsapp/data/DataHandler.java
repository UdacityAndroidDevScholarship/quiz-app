package com.developervishalsehgal.udacityscholarsapp.data;

import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

import java.util.List;

/**
 * Data layer abstraction. All data related communication should happen via this interface. This
 * is the only point of interaction with shared preferences, local sqlite database, firebase and
 * network
 *
 * @author kaushald
 *
 */

public interface DataHandler {

    void saveUserName(String userName);

    void saveUserEmail(String userEmail);

    void saveUsertrack(String userTrack);

    String getUserEmail();

    List<Quiz> getQuizzes();

}
