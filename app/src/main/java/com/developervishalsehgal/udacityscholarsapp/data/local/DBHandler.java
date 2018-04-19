package com.developervishalsehgal.udacityscholarsapp.data.local;

import android.content.Context;

/**
 * The oly point of interaction with local Database
 *
 * @Author kaushald
 */
public class DBHandler {
    private static DBHandler INSTANCE = null;

    static DBHandler getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DBHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBHandler(context);
                }
            }
        }
        return INSTANCE;
    }

    private DBHandler(Context context) {
    }
}
