package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

/**
 * Created by pramod on 22/4/18.
 */

public class QuestionContract {
    public static final class QuestionEntry implements BaseColumns {
        //Table name for questions.
        public static final String TABLE_QUESTION = "questions";
        //All column name for table TABLE_QUESTION.
        public static final String COLUMN_QUESTION_ID = "id";
        public static final String COLUMN_QUESTION_DESC = "description";
        public static final String COLUMN_QUESTION_TYPE = "type";
        public static final String COLUMN_QUESTION_MARKS = "marks";

    }
}