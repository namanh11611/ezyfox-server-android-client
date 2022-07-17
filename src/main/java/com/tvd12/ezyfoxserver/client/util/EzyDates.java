package com.tvd12.ezyfoxserver.client.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public final class EzyDates {

    private EzyDates () {}

    // =================== java 7 ===============
    public static String format (long millis) {
        return format(millis, getPattern());
    }

    public static String format (Date date) {
        return format(date, getPattern());
    }

    public static Date parse (String source) {
        return parse(source, getPattern());
    }

    public static String format (long millis, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(millis);
    }

    public static String format (Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date parse (String source, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String getPattern () {
        return "yyyy-MM-dd'T'HH:mm:ss:SSS";
    }

    // =========================================
    public static boolean between (Date date, Date before, Date after) {
        long time = date.getTime();
        return time >= before.getTime() && time <= after.getTime();
    }
}
