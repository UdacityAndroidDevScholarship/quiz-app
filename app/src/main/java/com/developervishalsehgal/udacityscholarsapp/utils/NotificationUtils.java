package com.developervishalsehgal.udacityscholarsapp.utils;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
import com.developervishalsehgal.udacityscholarsapp.ui.home.HomeActivity;

public class NotificationUtils {

    /**
     * Description - creates a notification channel based on given channelId and channelName if the channel does not exists
     *
     * @param context     - Context of the calling activity
     * @param channelId   - Id for the notification channel
     * @param channelName - Name for the notification channel
     */
    @TargetApi(26)
    private static void createNotificationChannel(Context context, String channelId, String channelName) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null && notificationManager.getNotificationChannel(channelId) == null) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /**
     * Description - creates a notification based on passed notification object and a notification channel for the given channelId
     * if it does not exist
     *
     * @param context      - Context of the calling activity
     * @param notification - Model object of notification
     * @param channelId    - Id for the notification channel
     */
    public static void createNotification(Context context, Notification notification, String channelId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel(context, channelId, notification.getType());
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder.setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(getSmallIcon(notification.getType().toLowerCase()))
                .setLargeIcon(getLargeIcon(context, notification.getType()))
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getDescription())
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        notification.getDescription()))
                .setDefaults(android.app.Notification.DEFAULT_VIBRATE)
                .setContentIntent(createContentIntent(context, notification.getAction(),
                        notification.getExtra1(), notification.getExtra2()))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        if (notificationManager != null) {
            notificationManager.notify((int) (System.currentTimeMillis() / 1000), builder.build());
        }
    }

    /**
     * @param context - Context of the calling activity
     * @return - returns PendingIntent
     */
    private static PendingIntent createContentIntent(Context context, String action, String extra1, String extra2) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(AppConstants.KEY_ACTION, action);
        intent.putExtra(AppConstants.KEY_EXTRA_1, extra1);
        intent.putExtra(AppConstants.KEY_EXTRA_2, extra2);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /**
     * @param context          - Context of the calling activity
     * @param notificationType - Type of notification
     * @return Bitmap - returns Bitmap object according to type of notification
     */
    private static Bitmap getLargeIcon(Context context, String notificationType) {
        @DrawableRes int drawableResourceId;
        switch (notificationType) {
            case "quiz":
                drawableResourceId = R.drawable.ic_udacity;
                break;
            case "resource":
                drawableResourceId = R.drawable.ic_udacity;
                break;
            case "discussion":
                drawableResourceId = R.drawable.ic_udacity;
                break;
            case "announcement":
                drawableResourceId = R.drawable.ic_udacity;
                break;
            default:
                drawableResourceId = 0;
        }
        return BitmapFactory.decodeResource(context.getResources(), drawableResourceId);
    }

    /**
     * @param notificationType - Type of notification
     * @return DrawableResource - image resource according to type of notification
     */
    @DrawableRes
    private static int getSmallIcon(String notificationType) {
        switch (notificationType) {
            case "quiz":
                return R.drawable.ic_udacity;
            case "resource":
                return R.drawable.ic_udacity;
            case "discussion":
                return R.drawable.ic_udacity;
            case "announcement":
                return R.drawable.ic_udacity;
            default:
                return 0;
        }
    }
}
