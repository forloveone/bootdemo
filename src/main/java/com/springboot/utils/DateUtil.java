package com.springboot.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类 基于org.apache.commons.lang3.time.DateUtils;
 */
public class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /*
        使用ThreadLocal 来避免sdf的线程问题
        private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        private static ThreadLocal threadLocal = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };
     */


    /**
     * 把String格式(yyyy-MM-dd)的时间类型,转换成毫秒值
     *
     * @param date yyyy-MM-dd形式的时间字符串
     * @return long 时间对应的毫秒值
     * @throws ParseException
     */
    public static long convertTimeToLong(String date) throws ParseException {
        Date parseTime = DateUtils.parseDate(date, "yyyy-MM-dd");
        return parseTime.getTime();
    }

    //获取yyyy-MM-dd
    public static String getShortDate(Date date) {
        return sdf.format(date);
    }

    /**
     * 把毫秒值的time转换成为Date类型
     *
     * @param date 毫秒值转Date
     * @return Date
     */
    public static Date convertLongToTime(long date) {
        return new Date(date);
    }

    /**
     * 解析身份证中的生日作为String
     *
     * @param card 身份证id号
     * @return String yyyyMMdd形式的生日
     */
    public static String parseIdCardForBirthday(String card) {
        return card.substring(6, 14);
    }

    /**
     * 解析身份证中的生日做为Long
     *
     * @param card
     * @return long
     * @throws ParseException
     */
    public static long parseIdCardForBirthdayMilli(String card) throws ParseException {
        String birthday = parseIdCardForBirthday(card);
        Date date = DateUtils.parseDate(birthday, "yyyyMMdd");
        return date.getTime();
    }

    /**
     * 根据身份证来确定一个人的确切年龄
     *
     * @param card 身份证
     * @return
     */
    public static int parseIdCardforAge(String card) {
        //年  月   日    2016 10 20  1990 01 10
        Integer year = Integer.valueOf(parseIdCardForBirthday(card).substring(0, 4));
        Integer month = Integer.valueOf(parseIdCardForBirthday(card).substring(4, 6));
        Integer day = Integer.valueOf(parseIdCardForBirthday(card).substring(6, 8));
        String dateNow = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String[] dates = dateNow.split("-");
        int age = 0;
        //生日判定,根据月份,和天数,来判定真实年龄
        if (Integer.valueOf(dates[1]) > month) {
            age = Integer.valueOf(dates[0]) - year;
        } else if (Integer.valueOf(dates[1]) < month) {
            age = Integer.valueOf(dates[0]) - year - 1;
        } else {
            if (Integer.valueOf(dates[2]) >= day) {
                age = Integer.valueOf(dates[0]) - year;
            } else {
                age = Integer.valueOf(dates[0]) - year - 1;
            }
        }
        return age;
    }
}
