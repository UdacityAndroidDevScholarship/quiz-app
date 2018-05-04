package com.developervishalsehgal.udacityscholarsapp.services;

import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
import com.developervishalsehgal.udacityscholarsapp.utils.NotificationUtils;
import android.util.Log;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FBMessagingService extends FirebaseMessagingService{
    private static final String TAG = FBMessagingService.class.getSimpleName();
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_FROM = "from";
    private static final String KEY_TYPE = "type";
    private static final String KEY_ACTION = "action";
    private static final String KEY_EXTRA_TYPE_1 = "extra1";
    private static final String KEY_EXTRA_TYPE_2 = "extra2";


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
      
//        check if message contains a data payload
        if(remoteMessage != null && remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload");
//          TODO: Check if data needs to be processed takes more than 10sec
            storeNotification(remoteMessage);
          raiseSystemNotification(remoteMessage);
        }

//        Check if message contains a notification payload
        if(remoteMessage != null && remoteMessage.getNotification() != null){
            Log.d(TAG, "Message notification payload");
//          TODO: Handle notification payload here
        }
    }
  
  //    Store the message data into local database
    private void storeNotification(RemoteMessage remoteMessage){
        Notification notification = new Notification();
        Map<String, String> data = remoteMessage.getData();

        notification.setTimeStamp(System.currentTimeMillis());
        notification.setDescription(data.get(KEY_DESCRIPTION));
        notification.setTitle(data.get(KEY_TITLE));
        notification.setFrom(data.get(KEY_FROM));
        notification.setType(data.get(KEY_TYPE));
        notification.setAction(data.get(KEY_ACTION));
        notification.setExtra1(data.get(KEY_EXTRA_TYPE_1));
        notification.setExtra2(data.get(KEY_EXTRA_TYPE_2));

        DataHandlerProvider.provide().addNotification(notification);
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
