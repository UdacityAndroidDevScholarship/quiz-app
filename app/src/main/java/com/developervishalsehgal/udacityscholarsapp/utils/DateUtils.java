package com.developervishalsehgal.udacityscholarsapp.utils;

public class DateUtils {
    public static String getFormattedTime(long timeInMillis, long currentTime) {
        return android.text.format.DateUtils.getRelativeTimeSpanString(timeInMillis, currentTime, android.text.format.DateUtils.MINUTE_IN_MILLIS).toString();
    }
}
