package com.qualle.truegain.service.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatUtil {


    public static LocalDateTime toDate(String date) {

        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (RuntimeException e) {
            throw new RuntimeException("Unable to parse date: " + e.getMessage(), e);
        }
    }

    public static String toString(LocalDateTime dateTime) {

        try {
            return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (RuntimeException e) {
            throw new RuntimeException("Unable to format date: " + e.getMessage(), e);
        }
    }
}
