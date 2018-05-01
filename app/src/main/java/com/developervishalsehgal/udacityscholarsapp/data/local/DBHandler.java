package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;

import java.util.ArrayList;
import java.util.List;

import com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.*;
/**
 * The oly point of interaction with local Database
 *
 * @Author kaushald
 */
public class DBHandler {

    private static DBHandler INSTANCE = null;
    private SQLiteDatabase db;

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

//    Add notification details to the local database
    public void addNotification(Notification notification){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotificationEntry.COLUMN_TIMESTAMP, notification.getTimeStamp());
        contentValues.put(NotificationEntry.COLUMN_TITLE, notification.getTitle());
        contentValues.put(NotificationEntry.COLUMN_DESCRIPTION, notification.getDescription());
        contentValues.put(NotificationEntry.COLUMN_FROM, notification.getFrom());
        contentValues.put(NotificationEntry.COLUMN_TYPE, notification.getType());
        contentValues.put(NotificationEntry.COLUMN_ACTION, notification.getAction());
        contentValues.put(NotificationEntry.COLUMN_EXTRA_1, notification.getExtra1());
        contentValues.put(NotificationEntry.COLUMN_EXTRA_2, notification.getExtra2());

        db.insert(NotificationContract.NotificationEntry.TABLE_NAME, null, contentValues);
    }

//    List of column selected while querying the database
    private static final String[] PROJECTION_ALL_COLUMN = new String[] {
        NotificationEntry.COLUMN_TIMESTAMP,                       //0
        NotificationEntry.COLUMN_TITLE,                           //1
        NotificationEntry.COLUMN_DESCRIPTION,                     //2
        NotificationEntry.COLUMN_FROM,                            //3
        NotificationEntry.COLUMN_TYPE,                            //4
        NotificationEntry.COLUMN_ACTION,                          //5
        NotificationEntry.COLUMN_EXTRA_1,                         //6
        NotificationEntry.COLUMN_EXTRA_2};                        //7


//    Query the database to get all notification availabe
    public List<Notification> getAllNotification(int startFrom, int limit){
        List<Notification> notificationList = new ArrayList<>();
        Notification notification = new Notification();

        Cursor cursor =  db.query(NotificationContract.NotificationEntry.TABLE_NAME, PROJECTION_ALL_COLUMN, null,
            null, null, null, NotificationEntry.COLUMN_TIMESTAMP +" DESC");
        try {
            cursor.moveToFirst();
            do {
                notification.setTimeStamp(cursor.getLong(cursor.getColumnIndex(NotificationEntry.COLUMN_TIMESTAMP)));
                notification.setTitle(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_TITLE)));
                notification.setDescription(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_DESCRIPTION)));
                notification.setFrom(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_FROM)));
                notification.setType(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_TYPE)));
                notification.setAction(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_ACTION)));
                notification.setExtra1(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_EXTRA_1)));
                notification.setExtra2(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_EXTRA_2)));
                notificationList.add(notification);

            } while (cursor.moveToNext());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return notificationList;
    }

//  Query the database based on the search text entered by the user
    public List<Notification> searchNotifications(String query, int startFrom, int limit){
        List<Notification> searchNotificationList = new ArrayList<>();
        Notification searchNotification = new Notification();

        String selectionStatement = NotificationContract.NotificationEntry.COLUMN_DESCRIPTION + " LIKE?" + " OR "
            + NotificationContract.NotificationEntry.COLUMN_TITLE + " LIKE?";

        String[] selectionCriteria = {"%"+query+"%", "%"+query+"%"};

        Cursor cursor = db.query(NotificationContract.NotificationEntry.TABLE_NAME, PROJECTION_ALL_COLUMN, selectionStatement, selectionCriteria,
            null,null, NotificationEntry.COLUMN_TIMESTAMP + " DESC");

        try {
            cursor.moveToFirst();
                do{
                    searchNotification.setTimeStamp(cursor.getLong(cursor.getColumnIndex(NotificationEntry.COLUMN_TIMESTAMP)));
                    searchNotification.setTitle(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_TITLE)));
                    searchNotification.setDescription(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_DESCRIPTION)));
                    searchNotification.setFrom(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_FROM)));
                    searchNotification.setType(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_TYPE)));
                    searchNotification.setAction(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_ACTION)));
                    searchNotification.setExtra1(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_EXTRA_1)));
                    searchNotification.setExtra2(cursor.getString(cursor.getColumnIndex(NotificationEntry.COLUMN_EXTRA_2)));
                    searchNotificationList.add(searchNotification);
                }while (cursor.moveToNext());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return  searchNotificationList;
    }
}
