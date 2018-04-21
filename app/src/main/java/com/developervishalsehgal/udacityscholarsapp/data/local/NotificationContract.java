package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

public class NotificationContract {

    public NotificationContract(){}

    public static final class NotificationEntry implements BaseColumns {
        /*
        * Columns of Table notification
        */
        public static final String TABLE_NAME = "notification";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_TITLE ="title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_FROM = "from";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_ACTION = "action";
        public static final String COLUMN_EXTRA1 = "extra1";
        public static final String COLUMN_EXTRA2 = "extra2";

    }
}
