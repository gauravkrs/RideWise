package com.airtribe.ridewise.util;

public class TimeUtils {

    private TimeUtils(){}

    public static int getCurrentHour() {
        return java.time.LocalDateTime.now().getHour();
    }

    public static boolean isPeakHour() {
        int hour = getCurrentHour();
        return (hour >= 8 && hour < 11) || (hour >= 17 && hour <= 20);
    }
}
