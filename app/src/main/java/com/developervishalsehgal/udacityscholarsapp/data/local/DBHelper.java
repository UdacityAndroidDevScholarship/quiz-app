package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.developervishalsehgal.udacityscholarsapp.data.local.NotificationContract.*;

import com.developervishalsehgal.udacityscholarsapp.data.local.QuestionContract.*;
import com.developervishalsehgal.udacityscholarsapp.data.local.OptionContract.*;

/**
 * Local database for the app. Contains only one database which all the related tables.
 * Notifications, Attempt Quiz, Create Quiz, Forums, Cache etc.
 * TODO change the description after implementation
 *
 * @Author kaushald
 * @Author pramodbharti | Have implemented OnCreate() method for TABLE Question and options
 *
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = SQLiteOpenHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "quiz_app.db";
    private static final int DATABASE_VERSION = 1;

    //Create a Table to hold Notification data
    private static final String CREATE_NOTIFICATION_TABLE = "CREATE TABLE " +
            NotificationEntry.TABLE_NAME + " (" +
            NotificationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            NotificationEntry.COLUMN_TIMESTAMP + " INTEGER NOT NULL," +
            NotificationEntry.COLUMN_TITLE + " TEXT NOT NULL," +
            NotificationEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL," +
            NotificationEntry.COLUMN_FROM + " TEXT NOT NULL," +
            NotificationEntry.COLUMN_TYPE + " TEXT NOT NULL," +
            NotificationEntry.COLUMN_ACTION + " TEXT," +
            NotificationEntry.COLUMN_EXTRA_1 + " TEXT," +
            NotificationEntry.COLUMN_EXTRA_2 + " TEXT" +
            "); ";
    
    private static final String CREATE_QUESTION_TABLE = "CREATE TABLE " +
            QuestionEntry.TABLE_QUESTION + " (" +
            QuestionEntry.COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionEntry.COLUMN_QUESTION_DESC + " TEXT NOT NULL, " +
            QuestionEntry.COLUMN_QUESTION_TYPE + " TEXT NOT NULL, " +
            QuestionEntry.COLUMN_QUESTION_MARKS + " INTEGER NOT NULL);";
    
    private static final String CREATE_OPTION_TABLE = "CREATE TABLE " +
            OptionEntry.TABLE_OPTION + " (" +
            OptionEntry.COLUMN_OPTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            OptionEntry.COLUMN_OPTION_QUESTION_ID + " INTEGER NOT NULL, " +
            OptionEntry.COLUMN_OPTION_DESC + " TEXT NOT NULL, " +
            OptionEntry.COLUMN_OPTION_IS_CORRECT + " BOOLEAN NOT NULL, " +
            OptionEntry.COLUMN_OPTION_REMARKS + " TEXT, FOREIGN KEY(" +
            OptionEntry.COLUMN_OPTION_QUESTION_ID + ") REFERENCES " +
            QuestionEntry.TABLE_QUESTION + "(" +
            QuestionEntry.COLUMN_QUESTION_ID + "));";
  

    //Delete(Drop) Table if DB version changes
    private static final String DELETE_EXISTING_NOTIFICATION_TABLE = "DROP TABLE IF EXISTS " + NotificationEntry.TABLE_NAME;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTIFICATION_TABLE);
        db.execSQL(CREATE_QUESTION_TABLE);
        db.execSQL(CREATE_OPTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Can be added later
        db.execSQL(DELETE_EXISTING_NOTIFICATION_TABLE);
    }
}
