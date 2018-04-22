package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.developervishalsehgal.udacityscholarsapp.data.local.QuestionsContract.QuestionsEntry;

/**
 * Local database for the app. Contains only one database which all the related tables.
 * Notifications, Attempt Quiz, Create Quiz, Forums, Cache etc.
 *
 * TODO change the description after implementation
 *
 * @Author kaushald
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = SQLiteOpenHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "quiz_app.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_QUESTIONS_CREATE = "CREATE TABLE " + QuestionsEntry.TBL_QUESTIONS + " ("
                + QuestionsEntry.QUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QuestionsEntry.QUE_DESC + " TEXT NOT NULL, "
                + QuestionsEntry.QUE_IMG + " TEXT, "
                + QuestionsEntry.QUE_TYPE + " INTEGER NOT NULL, "
                + QuestionsEntry.MARKS + " INTEGER NOT NULL DEFAULT 0);";
        Log.e(LOG_TAG,SQL_QUESTIONS_CREATE);
        db.execSQL(SQL_QUESTIONS_CREATE);

        String SQL_OPTIONS_CREATE = "CREATE TABLE " + QuestionsEntry.TBL_OPTIONS + " ("
                + QuestionsEntry.OPT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QuestionsEntry.FK_QUE_ID + " INTEGER NOT NULL, "
                + QuestionsEntry.OPT_DESC + " TEXT NOT NULL, "
                + QuestionsEntry.IS_CORRECT + " BOOLEAN NOT NULL, "
                + QuestionsEntry.REMARKS + " TEXT);";
        Log.e(LOG_TAG,SQL_OPTIONS_CREATE);
        db.execSQL(SQL_OPTIONS_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
