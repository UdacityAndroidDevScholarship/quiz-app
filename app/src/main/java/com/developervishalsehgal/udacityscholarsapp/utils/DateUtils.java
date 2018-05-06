package com.developervishalsehgal.udacityscholarsapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vibhuti on 5/6/2018.
 */

public class DateUtils {

    public static String getDateForDiscussion(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
        String formattedDate = df.format(c);
        return formattedDate;
    }
}
