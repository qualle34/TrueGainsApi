package com.qualle.truegain.service.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

    public static long getDayNumber(LocalDateTime date) {

        try {
            return getDayNumber(date.toLocalDate());
        } catch (RuntimeException e) {
            throw new RuntimeException("Unable to parse date: " + e.getMessage(), e);
        }
    }

    public static long getDayNumber(LocalDate date) {

        try {
            return date.toEpochDay();
        } catch (RuntimeException e) {
            throw new RuntimeException("Unable to parse date: " + e.getMessage(), e);
        }
    }

}
