package com.developervishalsehgal.udacityscholarsapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private Utils() {
        // Utility class. Not for initialization
    }

    public static long getDateInSeconds(String dateyyyyMMdd) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date d = df.parse(dateyyyyMMdd);
            return d.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
