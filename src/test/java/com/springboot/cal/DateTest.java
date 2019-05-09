package com.springboot.cal;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    @Test
    public void test() throws ParseException {
        String s = "Wed Apr 24 12:46:35 CST 2019";
        String dateString = "Tue Apr 15 11:23:55 CST 2014";
        SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.ENGLISH);
        System.out.println(sfEnd.format(sfStart.parse(dateString)));
    }

    @Test
    public void test2() throws ParseException {
        Date date = DateUtils.parseDate("1990-12-01", "yyyy-MM-dd");
        System.out.println(date);
    }
}
