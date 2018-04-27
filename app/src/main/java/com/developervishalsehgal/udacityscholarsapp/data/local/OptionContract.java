package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

/**
 * Created by pramod on 27/4/18.
 */

public class OptionContract {
    public static final class OptionEntry implements BaseColumns{
        //Table name for options.
        public static final String TABLE_OPTION = "options";
        //All column name for table TABLE_OPTION.
        public static final String COLUMN_OPTION_ID = "id";
        //Foreign key referencing TABLE_QUESTION's COLUMN_QUESTION_ID
        public static final String COLUMN_OPTION_QUESTION_ID = "question_id";
        public static final String COLUMN_OPTION_DESC = "description";
        public static final String COLUMN_OPTION_IS_CORRECT = "is_correct";
        public static final String COLUMN_OPTION_REMARKS = "remarks";
    }
}
