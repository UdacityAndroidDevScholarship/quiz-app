package com.developervishalsehgal.udacityscholarsapp.services;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMTokenFetcher extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String fcmToken = FirebaseInstanceId.getInstance().getToken();
        saveToken(fcmToken);
    }

    private void saveToken(String fcmToken) {
        DataHandlerProvider.provide().updateFCMToken(fcmToken);
    }
}
