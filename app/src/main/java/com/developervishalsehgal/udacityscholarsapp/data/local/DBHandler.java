package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;

import java.util.ArrayList;
import java.util.List;

import static com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.NotificationEntry.COLUMN_ACTION;
import static com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.NotificationEntry.COLUMN_DESCRIPTION;
import static com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.NotificationEntry.COLUMN_EXTRA_1;
import static com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.NotificationEntry.COLUMN_EXTRA_2;
import static com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.NotificationEntry.COLUMN_FROM;
import static com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.NotificationEntry.COLUMN_TIMESTAMP;
import static com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.NotificationEntry.COLUMN_TITLE;
import static com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.NotificationEntry.COLUMN_TYPE;

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
        contentValues.put(COLUMN_TIMESTAMP, notification.getTimeStamp());
        contentValues.put(COLUMN_TITLE, notification.getTitle());
        contentValues.put(COLUMN_DESCRIPTION, notification.getDescription());
        contentValues.put(COLUMN_FROM, notification.getFrom());
        contentValues.put(COLUMN_TYPE, notification.getType());
        contentValues.put(COLUMN_ACTION, notification.getAction());
        contentValues.put(COLUMN_EXTRA_1, notification.getExtra1());
        contentValues.put(COLUMN_EXTRA_2, notification.getExtra2());

        db.insert(NotificationContract.NotificationEntry.TABLE_NAME, null, contentValues);
    }

//    List of column selected while querying the database
    private static final String[] PROJECTION = new String[] {
        COLUMN_TIMESTAMP,                       //0
        COLUMN_TITLE,                           //1
        COLUMN_DESCRIPTION,                     //2
        COLUMN_FROM,                            //3
        COLUMN_TYPE,                            //4
        COLUMN_ACTION,                          //5
        COLUMN_EXTRA_1,                         //6
        COLUMN_EXTRA_2};                        //7

//    Column index of the selected column
    private static final int COLUMN_TIMESTAMP_INDEX = 0;
    private static final int COLUMN_TITLE_INDEX = 1;
    private static final int COLUMN_DESCRIPTION_INDEX = 2;
    private static final int COLUMN_FROM_INDEX = 3;
    private static final int COLUMN_TYPE_INDEX = 4;
    private static final int COLUMN_ACTION_INDEX = 5;
    private static final int COLUMN_EXTRA_1_INDEX = 6;
    private static final int COLUMN_EXTRA_2_INDEX = 7;

//    Query the database to get all notification availabe
    public List<Notification> getAllNotification(int startFrom, int limit){
        List<Notification> notificationList = new ArrayList<>();
        Notification notification = new Notification();

        Cursor cursor =  db.query(NotificationContract.NotificationEntry.TABLE_NAME, PROJECTION, null,
            null, null, null, COLUMN_TIMESTAMP +"DESC");
        try {
            cursor.moveToFirst();
            do {
                notification.setTimeStamp(cursor.getLong(COLUMN_TIMESTAMP_INDEX));
                notification.setTitle(cursor.getString(COLUMN_TITLE_INDEX));
                notification.setDescription(cursor.getString(COLUMN_DESCRIPTION_INDEX));
                notification.setFrom(cursor.getString(COLUMN_FROM_INDEX));
                notification.setType(cursor.getString(COLUMN_TYPE_INDEX));
                notification.setAction(cursor.getString(COLUMN_ACTION_INDEX));
                notification.setExtra1(cursor.getString(COLUMN_EXTRA_1_INDEX));
                notification.setExtra2(cursor.getString(COLUMN_EXTRA_2_INDEX));
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

        Cursor cursor = db.query(NotificationContract.NotificationEntry.TABLE_NAME, PROJECTION, selectionStatement, selectionCriteria,
            null,null, NotificationContract.NotificationEntry.COLUMN_TIMESTAMP + "DESC");

        try {
            cursor.moveToFirst();
                do{
                    searchNotification.setTimeStamp(cursor.getLong(COLUMN_TIMESTAMP_INDEX));
                    searchNotification.setTitle(cursor.getString(COLUMN_TITLE_INDEX));
                    searchNotification.setDescription(cursor.getString(COLUMN_DESCRIPTION_INDEX));
                    searchNotification.setFrom(cursor.getString(COLUMN_FROM_INDEX));
                    searchNotification.setType(cursor.getString(COLUMN_TYPE_INDEX));
                    searchNotification.setAction(cursor.getString(COLUMN_ACTION_INDEX));
                    searchNotification.setExtra1(cursor.getString(COLUMN_EXTRA_1_INDEX));
                    searchNotification.setExtra2(cursor.getString(COLUMN_EXTRA_2_INDEX));
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
