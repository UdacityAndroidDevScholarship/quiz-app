package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.NotificationEntry;

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

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_NOTIFICATION_TABLE =

                "CREATE TABLE " + NotificationEntry.TABLE_NAME + " (" +

                        NotificationEntry._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        NotificationEntry.COLUMN_TIMESTAMP + " INTEGER, " +

                        NotificationEntry.COLUMN_TITLE + " TEXT NOT NULL, "   +

                        NotificationEntry.COLUMN_DESCRIPTION  + " TEXT NOT NULL, " +

                        NotificationEntry.COLUMN_TYPE  + " TEXT NOT NULL, "  +

                        NotificationEntry.COLUMN_ACTION  + " TEXT NOT NULL, "  +

                        NotificationEntry.COLUMN_EXTRA1  + " TEXT NOT NULL, "  +

                        NotificationEntry.COLUMN_EXTRA2  + " TEXT NOT NULL" + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_NOTIFICATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NotificationEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}