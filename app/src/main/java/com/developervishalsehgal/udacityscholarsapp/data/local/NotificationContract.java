package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

/**
 * Created by dell on 4/21/2018.
 */

public class NotificationContract{

    public static final class NotificationEntry implements BaseColumns{
        //DEFINING TABLE_NAME
        public static final String TABLE_NAME = "notification";
        //DEFINING COLUMN_NAMES
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_FROM = "from";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_ACTION = "action";
        public static final String COLUMN_EXTRA_1 = "extra1";
        public static final String COLUMN_EXTRA_2 = "extra2";
    }
}
