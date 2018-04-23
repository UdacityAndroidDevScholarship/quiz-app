package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

public class NotificationContract {

    private NotificationContract() {
    }

    public static final class NotificationEntry implements BaseColumns{

        //Name of the table
        public  static final String TABLE_NAME = "notifications";

        //Timestamp is the time when the notification is received
        public static final String COLUMN_TIMESTAMP = "timestamp";

        //Title of the notification
        public static final String COLUMN_TITLE = "title";

        //Description of the notification
        public static final String COLUMN_DESCRIPTION = "description";

        //Form stores the name of the preson who sent the notification
        public static final String COLUMN_FROM = "from";

        //Type describes the notification type
        public static final String COLUMN_TYPE = "type";

        //specifies the id of quiz  which is nullable
        public static final String COLUMN_ACTION = "action";

        //extra information about the notification which is nullable
        public static final String COLUMN_EXTRA1 = "extra1";

        // More extra information about the notification which is nullable
        public static final String COLUMN_EXTRA2 = "extra2";
    }
}
