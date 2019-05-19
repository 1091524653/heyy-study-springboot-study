package com.heyy.study.springbootstudycentral.utility;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @Classname DateTimeUtil
 * @Description utility for operating date time
 * @Date 2019/5/12 19:33
 * @Created by Breeze
 */
public class DateTimeUtil {
    public static final String ZONEID_UTC = "UTC";
    public static final String ZONEID_CHINA = "GMT+8";
    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE = "yyyy-MM-dd";

    /**
     * Date String to Date Object(The String format must include date&time fields)
     * @param dateString
     * @param pattern
     * @param timeZoneId
     * @return
     */
    public static Date string2DateTime(String dateString,String pattern,String timeZoneId){
        LocalDateTime dateTime = string2LocalDateTime(dateString,pattern);
        return toDate(dateTime,timeZoneId);
    }

    /**
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static LocalDateTime string2LocalDateTime(String dateString,String pattern){
        Objects.requireNonNull(dateString,"dateString is null" );
        Objects.requireNonNull(pattern,"pattern is null");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateString,formatter);
    }

    /**
     *
     * @param dateTime
     * @param timeZoneId
     * @return
     */
    public static Date toDate(LocalDateTime dateTime,String timeZoneId){
        Objects.requireNonNull(dateTime,"dateTime is null" );
        Objects.requireNonNull(timeZoneId,"timeZoneId is null" );
        return Date.from(dateTime.atZone(ZoneId.of(timeZoneId)).toInstant());
    }

    /**
     * Date String to Date Object(The String format must include date fields,exclude time fields)
     * @param dateString
     * @param pattern
     * @param timeZoneId
     * @return
     */
    public static Date string2DateStartOfDay(String dateString,String pattern,String timeZoneId){
        LocalDate localDate = string2LocalDate(dateString,pattern);
        return toDate(localDate, timeZoneId);
    }

    /**
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static LocalDate string2LocalDate(String dateString,String pattern){
        Objects.requireNonNull(dateString,"dateTime is null" );
        Objects.requireNonNull(pattern,"timeZoneId is null" );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateString,formatter);
    }

    /**
     *
     * @param localDate
     * @param timeZoneId
     * @return
     */
    public static Date toDate(LocalDate localDate,String timeZoneId){
        Objects.requireNonNull(localDate,"localDate is null" );
        Objects.requireNonNull(timeZoneId,"timeZoneId is null" );
        return Date.from(localDate.atStartOfDay(ZoneId.of(timeZoneId)).toInstant());
    }

    /**
     * transform a timestamp to time String
     * @param timestamp
     * @param timezoneId
     * @param pattern
     * @return
     */
    public static String timestamp2String(Long timestamp,String timezoneId,String pattern){
        Objects.requireNonNull(timezoneId,"timezoneId is null" );
        Objects.requireNonNull(timestamp,"timestamp is null" );
        Objects.requireNonNull(pattern,"pattern is null" );
        return localDateTime2String(tiemstamp2LocalDateTime(timestamp,timezoneId ),pattern );
    }

    /**
     *
     * @param timestamp
     * @param timezoneId
     * @return
     */
    public static LocalDateTime tiemstamp2LocalDateTime(Long timestamp,String timezoneId){
        Objects.requireNonNull(timezoneId,"timezoneId is null" );
        Objects.requireNonNull(timestamp,"timestamp is null" );
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.of(timezoneId)).toLocalDateTime();
    }

    /**
     * LocalDateTime to String
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String localDateTime2String(LocalDateTime localDateTime,String pattern){
        Objects.requireNonNull(localDateTime,"localDateTime is null" );
        Objects.requireNonNull(pattern,"pattern is null" );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * LocalDate to String
     * @param localDate
     * @param pattern
     * @return
     */
    public static String localDate2String(LocalDate localDate,String pattern){
        Objects.requireNonNull(localDate,"localDate is null" );
        Objects.requireNonNull(pattern,"pattern is null" );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }

    /**
     * date to string
     * @param date
     * @param pattern
     * @param timezoneId
     * @return
     */
    public static String dateToString(Date date,String pattern,String timezoneId){
        Objects.requireNonNull(date,"date is null" );
        Objects.requireNonNull(pattern,"pattern is null" );
        Objects.requireNonNull(timezoneId,"timezoneId is null" );
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),ZoneId.of(timezoneId) );
        return localDateTime2String(localDateTime, pattern);
    }

    /**
     * transform localDateTime Object to a timestamp
     * @param localDateTime
     * @param timezoneId
     * @return
     */
    public static Long localDateTime2Timestamp(LocalDateTime localDateTime,String timezoneId){
        Objects.requireNonNull(localDateTime,"localDateTime is null" );
        Objects.requireNonNull(timezoneId,"timezoneId is null" );
        return localDateTime.atZone(ZoneId.of(timezoneId)).toInstant().toEpochMilli();
    }

    /**
     * get current time,time pattern is yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDateTimeString(){
        return timestamp2String(System.currentTimeMillis(), ZONEID_CHINA, PATTERN_DATETIME);
    }


}
