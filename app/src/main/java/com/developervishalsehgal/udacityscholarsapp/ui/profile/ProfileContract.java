package com.developervishalsehgal.udacityscholarsapp.ui.profile;

import android.graphics.Bitmap;

import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

/**
 * Profile screen contract. Keeps profile View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface ProfileContract {

    /**
     * Profile view
     */
    interface View extends BaseView<Presenter> {

        void loadUserPic(String picUrl);

        void loadUserName(String userName);

        void loadSlackHandle(String slackHandle);

        void loadEmailAddress(String emailAddress);

        // TODO other profile related fields

    }

    /**
     * Profile presenter
     */
    interface Presenter extends BasePresenter {

        void saveProfile(Bitmap picture, String userName, String slackHandle, String status);

        // TODO other profile presenter methods here

    }

}
