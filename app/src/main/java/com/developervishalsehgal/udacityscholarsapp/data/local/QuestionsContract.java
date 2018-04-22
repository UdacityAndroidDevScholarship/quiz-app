package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

/**
 * Created by pramod on 22/4/18.
 */

public class QuestionsContract {
    public static abstract class QuestionsEntry implements BaseColumns {
        //Table name for questions.
        public static final String TBL_QUESTIONS = "tbl_questions";
        //All column name for table TBL_QUESTIONS.
        public static final String QUE_ID = BaseColumns._ID;
        public static final String QUE_DESC = "que_desc";
        public static final String QUE_IMG = "bg_image";
        public static final String QUE_TYPE = "que_type";
        public static final String MARKS = "marks";

        //Constant for que_type column
        public static final int SUBJECTIVE = 0;
        public static final int SINGLE_CHOICE = 1;
        public static final int MULTIPLE_CHOICE = 2;

        //Table name for options.
        public static final String TBL_OPTIONS = "tbl_options";
        //All column name for table TBL_OPTIONS.
        public static final String OPT_ID = BaseColumns._ID;
        //Foreign key referencing TBL_QUESTIONS QUE_ID
        public static final String FK_QUE_ID = "que_id";
        public static final String OPT_DESC = "opt_desc";
        public static final String IS_CORRECT = "is_correct";
        public static final String REMARKS = "remarks";

        /**
         * Returns whether or not the given question type is {@link #SUBJECTIVE}, {@link #SINGLE_CHOICE,
         * or {@link #MULTIPLE_CHOICE}.
         */
        public static boolean isValidQueType(int type) {
            if (type == SUBJECTIVE || type == SINGLE_CHOICE || type == MULTIPLE_CHOICE)
                return true;
            return false;
        }
    }
}