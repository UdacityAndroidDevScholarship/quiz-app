package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

/**
 * Notification Contract Class
 */
public class NotificationContract {

    /**
     * Notification Entry Class defining the columns of Notification Table.
     */
    public static final class NotificationEntry implements BaseColumns {

        public static final String TABLE_NAME = "notification";

        public static final String COLUMN_TIMESTAMP = "timestamp";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_DESCRIPTION = "description";

        public static final String COLUMN_FROM = "from";

        public static final String COLUMN_TYPE = "type";

        public static final String COLUMN_ACTION = "action";

        public static final String COLUMN_EXTRA1 = "extra1";

        public static final String COLUMN_EXTRA2 = "extra2";

    }

    /**
     * SQL Statement to create the Notification Table.
     */
    public static final String SQL_CREATE_NOTIFICATION_TABLE = "CREATE TABLE " +
            NotificationEntry.TABLE_NAME + " (" +
            NotificationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NotificationEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            NotificationEntry.COLUMN_TITLE + " TEXT NOT NULL," +
            NotificationEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL," +
            NotificationEntry.COLUMN_FROM + " TEXT NOT NULL," +
            NotificationEntry.COLUMN_TYPE + " TEXT NOT NULL," +
            NotificationEntry.COLUMN_ACTION + " INTEGER," +
            NotificationEntry.COLUMN_EXTRA1 + " TEXT," +
            NotificationEntry.COLUMN_EXTRA2 + " TEXT" +
            ");";

}
