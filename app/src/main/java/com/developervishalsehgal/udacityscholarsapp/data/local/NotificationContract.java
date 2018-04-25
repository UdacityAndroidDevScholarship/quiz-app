package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

/**
 * Created by akshay on 25/4/18.
 */

public class NotificationContract {

    // Inner class for defining the columns in the table
    public static final class NotificationEntry implements BaseColumns {

        public static final String TABLE_NAME = "notificationTable";

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