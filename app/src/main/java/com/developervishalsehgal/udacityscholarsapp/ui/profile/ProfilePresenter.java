package com.developervishalsehgal.udacityscholarsapp.ui.profile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.data.models.User;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View mView;
    private DataHandler mDataHandler;

    public ProfilePresenter(ProfileContract.View view) {
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();

    }

    @Override
    public void saveProfile(Bitmap picture, String slackHandle, String status) {

        // Upload the image in firebase storage
        mDataHandler.uploadProfilePic(picture, new DataHandler.Callback<String>() {
            @Override
            public void onResponse(String result) {
                // This result is the public URL of the bitmap we just uploaded
                if (result != null && !result.isEmpty()) {
                    mDataHandler.saveUserPic(result);
                }
                mDataHandler.saveSlackHandle(slackHandle);
                mDataHandler.saveStatus(status);

                mDataHandler.setUserInfo(new DataHandler.Callback<Void>() {
                    @Override
                    public void onResponse(Void result) {
                        mView.onProfileSaved();
                    }

                    @Override
                    public void onError() {
                        mView.onSaveError();
                    }
                });
            }

            @Override
            public void onError() {
                mView.onPictureUploadError();
            }
        });
    }

    @Override
    public void saveProfile(String pictureUrl, String slackHandle, String status) {

        if (pictureUrl != null && !pictureUrl.isEmpty()) {
            mDataHandler.saveUserPic(pictureUrl);
        }
        mDataHandler.saveSlackHandle(slackHandle);
        mDataHandler.saveStatus(status);

        mDataHandler.setUserInfo(new DataHandler.Callback<Void>() {
            @Override
            public void onResponse(Void result) {
                mView.onProfileSaved();
            }

            @Override
            public void onError() {
                mView.onSaveError();
            }
        });
    }

    @Override
    public void start(@Nullable Bundle extras) {

    }

    @Override
    public void destroy() {
        this.mView = null;
    }
}
