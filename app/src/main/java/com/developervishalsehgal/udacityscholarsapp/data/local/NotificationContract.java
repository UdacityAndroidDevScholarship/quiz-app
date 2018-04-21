package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

/**
 * Created by nihanth_2 on 4/21/2018.
 */

public final class NotificationContract {        //final has been included to avoid any inheritance can be removed if wanted


    public static final class NotificationEntry implements BaseColumns {

        public static final String TABLE_NAME = "notification_table";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_FROM = "from";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_ACTION = "action";
        public static final String COLUMN_EXTRA1 = "extra1";
        public static final String COLUMN_EXTRA2 = "extra2";
    }
}

