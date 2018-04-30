package com.developervishalsehgal.udacityscholarsapp.services;

import com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract;
import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
import com.developervishalsehgal.udacityscholarsapp.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FBMessagingService extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> receivedData = remoteMessage.getData();
        Notification notification = new Notification();
        notification.setTitle(receivedData.get(NotificationContract.NotificationEntry.COLUMN_TITLE));
        notification.setAction(receivedData.get(NotificationContract.NotificationEntry.COLUMN_ACTION));
        notification.setDescription(receivedData.get(NotificationContract.NotificationEntry.COLUMN_DESCRIPTION));
        notification.setFrom(receivedData.get(NotificationContract.NotificationEntry.COLUMN_FROM));
        notification.setType(receivedData.get(NotificationContract.NotificationEntry.COLUMN_TYPE));
        notification.setExtra1(receivedData.get(NotificationContract.NotificationEntry.COLUMN_EXTRA_1));
        notification.setExtra2(receivedData.get(NotificationContract.NotificationEntry.COLUMN_EXTRA_2));

        NotificationUtils.createNotification(this, notification, notification.getType());
        // TODO: Check for data and notification payload from remoteMessage and handle it accordingly
    }


}
