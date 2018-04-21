package com.developervishalsehgal.udacityscholarsapp.data.local;


import android.provider.BaseColumns;

/*
 * Defines table and column names for the notification database
 */

public class NotificationContract {

    private NotificationContract() {
    }

    /* Inner class that defines the contents of the notifications table */

    public static final class NotificationEntry implements BaseColumns {

        /* Used as the name of our table */
        public static final String TABLE_NAME = "notifications";

        /* Time when notification is received stored as Timestamp in our table */
        public static final String COLUMN_TIMESTAMP = "timestamp";

        /* Title of the notification stored as Text */
        public static final String COLUMN_TITLE = "title";

        /* Description in the notification stored as Text */
        public static final String COLUMN_DESCRIPTION = "description";

        /* Author of the notification stored as Text*/
        public static final String COLUMN_FROM = "from";

        /* Type of notification stored as Text */
        public static final String COLUMN_TYPE = "type";

        /* ID of the quiz stored as Text */
        public static final String COLUMN_ACTION = "action";

        /* Extra info about the notification stored as Text */
        public static final String COLUMN_EXTRA1 = "extra1";

        /* Extra info about the notification stored as Text */
        public static final String COLUMN_EXTRA2 = "extra2";
    }
}
