package com.developervishalsehgal.udacityscholarsapp.ui.profile;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

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

        void loadUserTrack(String userTrack);

        void onPictureUploadError();

        void onSaveError();

        void onProfileSaved();

    }

    /**
     * Profile presenter
     */
    interface Presenter extends BasePresenter {

        void saveProfile(@NonNull Bitmap picture, String slackHandle, String status);

        void saveProfile(String pictureUrl, String slackHandle, String status);

    }

}
