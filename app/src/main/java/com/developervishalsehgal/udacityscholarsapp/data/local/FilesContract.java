package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.provider.BaseColumns;

/**
 * Files table contract based on schema by @pramodbharti. This class is kept for future when we
 * do implement FilesContract. Right now it is not implemented
 */
public class FilesContract {

    /**
     * Files Entry class defining the Files table columns.
     */
    public static final class FilesEntry implements BaseColumns {

        public static final String TABLE_NAME = "files";

        public static final String COLUMN_QUESTION_ID = "question_id";

        public static final String COLUMN_FILES_PATH = "path";

    }
}
