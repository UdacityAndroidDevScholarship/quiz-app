package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

/**
 * Question Contract Class based on schema by @pramodbharti
 */
class QuestionContract {

    private QuestionContract() {
        // To prevent accidental instantiation
    }

    /**
     * Question Entry Class defining the columns of Question Table.
     */
    public static final class QuestionEntry implements BaseColumns {

        public static final String TABLE_NAME = "questions";

        public static final String COLUMN_DESCRIPTION = "description";

        public static final String COLUMN_MARKS = "marks";

        public static final String COLUMN_TYPE = "type";

    }

}
