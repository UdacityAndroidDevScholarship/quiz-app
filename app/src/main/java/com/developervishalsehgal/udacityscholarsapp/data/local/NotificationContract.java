package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

public class NotificationContract {

    //to prevent someone to accidently instantiate the contract class

//    private NotificationContract() {
//    }

    // Inner class to hold the contents of the notifications table
    public static final class NotificationEntry implements BaseColumns {

        public static final String TABLE_NAME = "notifications";
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

