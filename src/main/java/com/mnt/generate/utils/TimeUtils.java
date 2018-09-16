package com.mnt.generate.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeUtils {
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }


    public static Date getCurrentDate() {
        return new Date();
    }


    public static Date handleSearchEndTime(Date endTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endTime);
        calendar.set(10, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime();
    }


    public static String formatDate(Date date, String pattern) {
        if ((pattern == null) || (pattern.isEmpty())) {
            pattern = "yyyy-MM-dd";
        }
        if (date == null) {
            date = getCurrentDate();
        }

        DateFormat format = new SimpleDateFormat(pattern);

        return format.format(date);
    }


    public static Date getMonthStart() {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(getCurrentDate());
        cDay.set(5, 1);
        cDay.set(11, 0);
        cDay.set(12, 0);
        cDay.set(14, 0);
        cDay.set(13, 0);
        return cDay.getTime();
    }

    public static void main(String[] args) {
        System.err.println(getMonthStart());
    }
}