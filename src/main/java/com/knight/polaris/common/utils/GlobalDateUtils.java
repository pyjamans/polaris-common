package com.knight.polaris.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 通用时间工具类
 *
 * 主要用于时间类型之间的转换工具
 *
 * @author pyjaman
 * @date 2020-12-14
 */
public class GlobalDateUtils {

    public static final DateTimeFormatter BASIC_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String LOCAL_TIME_MAX = "23:59:59";

    private static final int HOUR= 3600000;

    private static final int MINUTE = 60000;

    private static final int SECOND = 1000;

    /**
     * LocalDateTime to Long
     */
    public static Long toTimeMillis(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDate to Long
     */
    public static Long toTimeMillis(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * Date to Long
     */
    public static Long toTimeMillis(Date date) {
        return date.getTime();
    }

    /**
     * String to Long
     */
    public static Long toTimeMillis(String dateString) {
        return toTimeMillis(dateString, BASIC_FORMATTER);
    }

    /**
     * String + pattern to Long
     */
    public static Long toTimeMillis(String dateString, String pattern) {
        return toTimeMillis(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * String + DateTimeFormatter to Long
     */
    public static Long toTimeMillis(String dateString, DateTimeFormatter formatter) {
        LocalDateTime localDateTime = toLocalDateTime(dateString, formatter);
        return toTimeMillis(localDateTime);
    }

    /**
     * LocalDate to LocalDateTime min
     */
    public static LocalDateTime toLocalDateTimeMin(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    /**
     * LocalDate to LocalDateTime max
     */
    public static LocalDateTime toLocalDateTimeMax(LocalDate localDate) {
        return toLocalDateTime(localDate, LocalTime.parse(LOCAL_TIME_MAX));
    }

    /**
     * LocalDate + LocalTime to LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * Date to LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Long to LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Long timeMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault());
    }

    /**
     * String to LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String dateString) {
        return toLocalDateTime(dateString, BASIC_FORMATTER);
    }

    /**
     * String + pattern to LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String dateString, String pattern) {
        return toLocalDateTime(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * String + DateTimeFormatter to LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String dateString, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateString, formatter);
    }

    /**
     * Long to LocalDate
     */
    public static LocalDate toLocalDate(Long timeMillis) {
        return toLocalDateTime(timeMillis).toLocalDate();
    }

    /**
     * LocalDateTime to Long
     */
    public static LocalDate toLocalDate(Date date) {
        return toLocalDateTime(date).toLocalDate();
    }

    /**
     * String to LocalDate
     */
    public static LocalDate toLocalDate(String dateString) {
        return toLocalDate(dateString, BASIC_FORMATTER);
    }

    /**
     * String + pattern to LocalDate
     */
    public static LocalDate toLocalDate(String dateString, String pattern) {
        return toLocalDate(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * String + DateTimeFormatter to LocalDate
     */
    public static LocalDate toLocalDate(String dateString, DateTimeFormatter formatter) {
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * LocalDateTime to Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate to Date
     */
    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * String to Date
     */
    public static Date toDate(String dateString) {
        return toDate(dateString, BASIC_FORMATTER);
    }

    /**
     * String + pattern to Date
     */
    public static Date toDate(String dateString, String pattern) {
        return toDate(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * String + DateTimeFormatter to Date
     */
    public static Date toDate(String dateString, DateTimeFormatter formatter) {
        LocalDateTime localDateTime = toLocalDateTime(dateString, formatter);
        return toDate(localDateTime);
    }

    /**
     * Long to String
     */
    public static String toString(Long timeMillis) {
        LocalDateTime localDateTime = toLocalDateTime(timeMillis);
        return toString(localDateTime);
    }

    /**
     * Long + pattern to String
     */
    public static String toString(Long timeMillis, String pattern) {
        LocalDateTime localDateTime = toLocalDateTime(timeMillis);
        return toString(localDateTime, pattern);
    }

    /**
     * Long + DateTimeFormatter to String
     */
    public static String toString(Long timeMillis, DateTimeFormatter formatter) {
        LocalDateTime localDateTime = toLocalDateTime(timeMillis);
        return toString(localDateTime, formatter);
    }

    /**
     * Date to String
     */
    public static String toString(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return toString(localDateTime);
    }

    /**
     * Date + pattern to String
     */
    public static String toString(Date date, String pattern) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return toString(localDateTime, pattern);
    }

    /**
     * Date + DateTimeFormatter to String
     */
    public static String toString(Date date, DateTimeFormatter formatter) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return toString(localDateTime, formatter);
    }

    /**
     * LocalDateTime to String
     */
    public static String toString(LocalDateTime localDateTime) {
        return BASIC_FORMATTER.format(localDateTime);
    }

    /**
     * LocalDateTime + pattern to String
     */
    public static String toString(LocalDateTime localDateTime, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }

    /**
     * LocalDateTime + DateTimeFormatter to String
     */
    public static String toString(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return formatter.format(localDateTime);
    }

    /**
     * LocalDate to String
     */
    public static String toString(LocalDate localDate) {
        return BASIC_FORMATTER.format(localDate);
    }

    /**
     * LocalDate + pattern to String
     */
    public static String toString(LocalDate localDate, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDate);
    }

    /**
     * LocalDate + DateTimeFormatter to String
     */
    public static String toString(LocalDate localDate, DateTimeFormatter formatter) {
        return formatter.format(localDate);
    }

    /**
     * now to String
     */
    public static String nowToString() {
        return BASIC_FORMATTER.format(LocalDateTime.now());
    }

    /**
     * now + pattern to String
     */
    public static String nowToString(String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(LocalDateTime.now());
    }

    public static String transTime(Integer timeMillis) {
        if(timeMillis == null || timeMillis == 0) {
            return "00:00:00.000";
        }
        return transTime((long) timeMillis);
    }

    /**
     * long to time string
     *
     * eg: 96000 => 00:01:36.000 即1分36秒
     */
    public static String transTime(Long timeMillis) {
        if(timeMillis == null || timeMillis == 0) {
            return "00:00:00.000";
        }
        StringBuilder builder = new StringBuilder();
        int hour = (int)(timeMillis / HOUR);
        builder.append(hour < 10 ? "0" : "").append(hour);
        timeMillis -= HOUR * hour;
        int minute = (int)(timeMillis / MINUTE);
        builder.append(minute < 10 ? ":0" : ":").append(minute);
        timeMillis -= MINUTE * minute;
        int second = (int)(timeMillis / SECOND);
        builder.append(second < 10 ? ":0" : ":").append(second);
        int millisecond = (int)(timeMillis - SECOND * second);
        builder.append('.').append(millisecond);
        return builder.toString();
    }

}
