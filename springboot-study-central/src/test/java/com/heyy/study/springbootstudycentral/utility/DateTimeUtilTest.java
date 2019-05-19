package com.heyy.study.springbootstudycentral.utility;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Classname DateTimeUtilTest
 * @Description TODO
 * @Date 2019/5/12 20:44
 * @Created by Breeze
 */
public class DateTimeUtilTest {

    @Test
    public void string2LocalDateTime() {
        String dateTime = "2019-05-12 20:45:45";
        String pattern = "yyyy-MM-dd HH:mm:ss";
        String timeZoneId = "GMT+8";
        System.out.println(DateTimeUtil.string2DateTime(dateTime,pattern ,timeZoneId ));
    }

    @Test
    public void string2Date() {
        String dateTime = "2019-05-12";
        String pattern = "yyyy-MM-dd";
        String timeZoneId = "GMT+8";
        System.out.println(DateTimeUtil.string2DateStartOfDay(dateTime,pattern ,timeZoneId ));
    }

    @Test
    public void timestamp2String() {
        Long time  = System.currentTimeMillis();
        System.out.println(DateTimeUtil.timestamp2String(time,DateTimeUtil.ZONEID_CHINA ,DateTimeUtil.PATTERN_DATETIME ));
    }

    @Test
    public void dateToString() {
        Date date = new Date();
        System.out.println(DateTimeUtil.dateToString(date, DateTimeUtil.PATTERN_DATETIME, DateTimeUtil.ZONEID_CHINA));
    }
}