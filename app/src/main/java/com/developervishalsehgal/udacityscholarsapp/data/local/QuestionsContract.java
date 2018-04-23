package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

/**
 * Created by pramod on 22/4/18.
 */

public class QuestionsContract {
    public static abstract class QuestionsEntry implements BaseColumns {
        //Table name for questions.
        public static final String TABLE_QUESTIONS = "questions";
        //All column name for table TABLE_QUESTIONS.
        public static final String COLUMN_QUESTION_ID = "id";
        public static final String COLUMN_QUESTION_DESC = "description";
        public static final String COLUMN_QUESTION_TYPE = "type";
        public static final String COLUMN_QUESTION_MARKS = "marks";

        //Table name for options.
        public static final String TABLE_OPTIONS = "options";
        //All column name for table TABLE_OPTIONS.
        public static final String COLUMN_OPTION_ID = "id";
        //Foreign key referencing TABLE_QUESTIONS's COLUMN_QUESTION_ID
        public static final String COLUMN_OPTION_QUESTION_ID = "question_id";
        public static final String COLUMN_OPTION_DESC = "description";
        public static final String COLUMN_OPTION_IS_CORRECT = "is_correct";
        public static final String COLUMN_OPTION_REMARKS = "remarks";

    }
}