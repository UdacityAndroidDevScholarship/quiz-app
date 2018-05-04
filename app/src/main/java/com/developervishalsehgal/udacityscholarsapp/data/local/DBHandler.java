package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;

import java.util.ArrayList;
import java.util.List;

/**
 * The only point of interaction with local Database
 *
 * @Author kaushald
 */
public class DBHandler {

    private static DBHandler INSTANCE = null;
    private SQLiteDatabase db;

    /**
     * Dual null check, synchronized get instance method
     * @param context Application context
     * @return The unique instance of this class
     */
    public static DBHandler getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DBHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBHandler(context);
                }
            }
        }
        return INSTANCE;
    }

    private DBHandler(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    /**
     * Project representing ALL the columns of notifications table
     */
    private static final String[] NOTIFICATION_ALL_COLUMN = new String[]{
            NotificationContract.NotificationEntry.COLUMN_TIMESTAMP,    //0
            NotificationContract.NotificationEntry.COLUMN_TITLE,        //1
            NotificationContract.NotificationEntry.COLUMN_DESCRIPTION,  //2
            NotificationContract.NotificationEntry.COLUMN_FROM,         //3
            NotificationContract.NotificationEntry.COLUMN_TYPE,         //4
            NotificationContract.NotificationEntry.COLUMN_ACTION,       //5
            NotificationContract.NotificationEntry.COLUMN_EXTRA_1,      //6
            NotificationContract.NotificationEntry.COLUMN_EXTRA_2};     //7

    /**
     * Adds a notification to local database
     *
     * @param notification {@link Notification} object to e saved
     */
    public void addNotification(Notification notification) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotificationContract.NotificationEntry.COLUMN_TIMESTAMP, notification.getTimeStamp());
        contentValues.put(NotificationContract.NotificationEntry.COLUMN_TITLE, notification.getTitle());
        contentValues.put(NotificationContract.NotificationEntry.COLUMN_DESCRIPTION, notification.getDescription());
        contentValues.put(NotificationContract.NotificationEntry.COLUMN_FROM, notification.getFrom());
        contentValues.put(NotificationContract.NotificationEntry.COLUMN_TYPE, notification.getType());
        contentValues.put(NotificationContract.NotificationEntry.COLUMN_ACTION, notification.getAction());
        contentValues.put(NotificationContract.NotificationEntry.COLUMN_EXTRA_1, notification.getExtra1());
        contentValues.put(NotificationContract.NotificationEntry.COLUMN_EXTRA_2, notification.getExtra2());

        db.insert(NotificationContract.NotificationEntry.TABLE_NAME, null, contentValues);
    }

    /**
     * Gets all the notifications from local storage
     *
     * @param startFrom starting point for results (unimplemented)
     * @param limit     number of results to be returned (unimplemented)
     * @return a {@link List} of all {@link Notification} objects
     */
    public List<Notification> getAllNotification(int startFrom, int limit) {
        List<Notification> notificationList = new ArrayList<>();
        Notification notification = new Notification();

        try (Cursor cursor = db.query(NotificationContract.NotificationEntry.TABLE_NAME,
                NOTIFICATION_ALL_COLUMN,
                null,
                null,
                null,
                null,
                NotificationContract.NotificationEntry.COLUMN_TIMESTAMP + " DESC")) {

            int timeStampIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_TIMESTAMP);
            int titleIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_TITLE);
            int descriptionIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_DESCRIPTION);
            int fromIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_FROM);
            int typeIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_TYPE);
            int actionIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_ACTION);
            int extra1Index = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_EXTRA_2);
            int extra2Index = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_EXTRA_1);

            cursor.moveToFirst();

            do {
                notification.setTimeStamp(cursor.getLong(timeStampIndex));
                notification.setTitle(cursor.getString(titleIndex));
                notification.setDescription(cursor.getString(descriptionIndex));
                notification.setFrom(cursor.getString(fromIndex));
                notification.setType(cursor.getString(typeIndex));
                notification.setAction(cursor.getString(actionIndex));
                notification.setExtra1(cursor.getString(extra1Index));
                notification.setExtra2(cursor.getString(extra2Index));

                notificationList.add(notification);

            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notificationList;
    }

    /**
     * Searches for a text in notification title and description and returns a list
     *
     * @param query     the text to be searched
     * @param startFrom starting point for search (unimplemented)
     * @param limit     number of results to be returned (unimplemented)
     * @return a {@link List} of {@link Notification} objects matching the criteria
     */
    public List<Notification> searchNotifications(String query, int startFrom, int limit) {
        List<Notification> searchNotificationList = new ArrayList<>();
        Notification searchNotification = new Notification();

        String selectionStatement = NotificationContract.NotificationEntry.COLUMN_DESCRIPTION + " LIKE?" + " OR "
                + NotificationContract.NotificationEntry.COLUMN_TITLE + " LIKE?";

        String[] selectionCriteria = {"%" + query + "%", "%" + query + "%"};

        try (Cursor cursor = db.query(NotificationContract.NotificationEntry.TABLE_NAME,
                NOTIFICATION_ALL_COLUMN,
                selectionStatement,
                selectionCriteria,
                null,
                null,
                NotificationContract.NotificationEntry.COLUMN_TIMESTAMP + " DESC")) {

            int timeStampIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_TIMESTAMP);
            int titleIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_TITLE);
            int descriptionIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_DESCRIPTION);
            int fromIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_FROM);
            int typeIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_TYPE);
            int actionIndex = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_ACTION);
            int extra1Index = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_EXTRA_1);
            int extra2Index = cursor.getColumnIndex(NotificationContract.NotificationEntry.COLUMN_EXTRA_2);

            cursor.moveToFirst();

            do {
                searchNotification.setTimeStamp(cursor.getLong(timeStampIndex));
                searchNotification.setTitle(cursor.getString(titleIndex));
                searchNotification.setDescription(cursor.getString(descriptionIndex));
                searchNotification.setFrom(cursor.getString(fromIndex));
                searchNotification.setType(cursor.getString(typeIndex));
                searchNotification.setAction(cursor.getString(actionIndex));
                searchNotification.setExtra1(cursor.getString(extra1Index));
                searchNotification.setExtra2(cursor.getString(extra2Index));
                searchNotificationList.add(searchNotification);
            } while (cursor.moveToNext());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchNotificationList;
    }
}
