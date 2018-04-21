package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Local database for the app. Contains only one database which all the related tables.
 * Notifications, Attempt Quiz, Create Quiz, Forums, Cache etc.
 *
 * TODO change the description after implementation
 *
 * @Author kaushald
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quiz_app.db";
    private static final int DATABASE_VERSION = 1;

    /*
    * Create table query for Notification table stored in a String variable
     */
    private final static String SQL_CREATE_NOTIFICATION_TABLE =
            "CREATE TABLE " + NotificationContract.NotificationEntry.TABLE_NAME + " (" +
                    NotificationContract.NotificationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    NotificationContract.NotificationEntry.COLUMN_TIMESTAMP + "TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    NotificationContract.NotificationEntry.COLUMN_TITLE + "TEXT NOT NULL, " +
                    NotificationContract.NotificationEntry.COLUMN_DESCRIPTION + "TEXT NOT NULL, " +
                    NotificationContract.NotificationEntry.COLUMN_FROM + "TEXT NOT NULL, " +
                    NotificationContract.NotificationEntry.COLUMN_TYPE + "TEXT NOT NULL, " +
                    NotificationContract.NotificationEntry.COLUMN_ACTION + "TEXT, " +
                    NotificationContract.NotificationEntry.COLUMN_EXTRA1 + "TEXT, " +
                    NotificationContract.NotificationEntry.COLUMN_EXTRA2 + "TEXT" +
                    "); ";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_NOTIFICATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}