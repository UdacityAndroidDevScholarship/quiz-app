package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Local database for the app. Contains only one database which all the related tables.
 * Notifications, Attempt Quiz, Create Quiz, Forums, Cache etc.
 * <p>
 * TODO change the description after implementation
 *
 * @Author kaushald
 */
public class DBHelper extends SQLiteOpenHelper {

    /* A SQL statement that will create a table with all our notification data */
    private static final String SQL_CREATE_NOTIFICATION_TABLE = " CREATE TABLE " +
            NotificationContract.NotificationEntry.TABLE_NAME + " (" +
            /* _ID obtained by implement BaseColumns in our NotificationEntry class */
            NotificationContract.NotificationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NotificationContract.NotificationEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            NotificationContract.NotificationEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            NotificationContract.NotificationEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL," +
            NotificationContract.NotificationEntry.COLUMN_FROM + "TEXT NOT NULL, " +
            NotificationContract.NotificationEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
            NotificationContract.NotificationEntry.COLUMN_ACTION + "TEXT NOT NULL, " +
            NotificationContract.NotificationEntry.COLUMN_EXTRA1 + " TEXT, " +
            NotificationContract.NotificationEntry.COLUMN_EXTRA2 + " TEXT" +
            ");";

    private static final String DATABASE_NAME = "quiz_app.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* Executes the SQL statement on our SQLiteDatabase object */
        db.execSQL(SQL_CREATE_NOTIFICATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NotificationContract.NotificationEntry.TABLE_NAME);
        onCreate(db);
    }
}
