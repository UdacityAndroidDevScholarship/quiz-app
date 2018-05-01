package com.developervishalsehgal.udacityscholarsapp.services;

import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
import com.developervishalsehgal.udacityscholarsapp.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FBMessagingService extends FirebaseMessagingService{

    private static final String KEY_TITLE = "title";
    private static final String KEY_ACTION = "action";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_FROM = "from";
    private static final String KEY_TYPE = "type";
    private static final String KEY_EXTRA_1 = "extra_1";
    private static final String KEY_EXTRA_2 = "extra_2";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage != null) {
            raiseSystemNotification(remoteMessage);
        }
    }

    /**
     * Description - raises the system notification when the payload is received from server
     * @param remoteMessage - Payload received from server
     */
    private void raiseSystemNotification(RemoteMessage remoteMessage) {
        Map<String, String> receivedData = remoteMessage.getData();
        Notification notification = new Notification();
        notification.setTitle(receivedData.get(KEY_TITLE));
        notification.setAction(receivedData.get(KEY_ACTION));
        notification.setDescription(receivedData.get(KEY_DESCRIPTION));
        notification.setFrom(receivedData.get(KEY_FROM));
        notification.setType(receivedData.get(KEY_TYPE));
        notification.setExtra1(receivedData.get(KEY_EXTRA_1));
        notification.setExtra2(receivedData.get(KEY_EXTRA_2));

        NotificationUtils.createNotification(this, notification, notification.getType());
    }
}
