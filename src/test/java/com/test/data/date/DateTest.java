package com.test.data.date;

import com.springboot.utils.DateUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
    @Test
    public void test() throws ParseException {
        System.out.println(DateUtil.convertTimeToLong("1990-01-27"));
        ;
        long d = DateUtil.parseIdCardForBirthdayMilli("410504199001270513");
        System.out.println(d);

        int age = DateUtil.parseIdCardforAge("410504199001270513");
        System.out.println(age);
    }

    @Test
    public void test3() throws ParseException {
        String s = "Wed Apr 24 12:46:35 CST 2019";
        String dateString = "Tue Apr 15 11:23:55 CST 2014";
        SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.ENGLISH);
        System.out.println(sfEnd.format(sfStart.parse(dateString)));
    }

    @Test
    public void test2() throws ParseException {
        Date date = DateUtils.parseDate("1990-12-01", "yyyy-MM-dd");
        String yyyy年MM月dd日 = DateFormatUtils.format(new Date(), "yyyy年MM月dd日");
        System.out.println(yyyy年MM月dd日);
        System.out.println(date);
    }

    /**
     * 昨天的日期
     */
    @Test
    public void test5() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(calendar.getTime()));
    }

    /**
     * 判断某个日期是当前的最后一天
     */
    @Test
    public void test6() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2004);
        c.set(Calendar.MONTH, 0);// 0表示1月,以后顺延
        c.set(Calendar.DAY_OF_MONTH, 30);// 一个月中的某天,如果超出会自动跳到下一个月.
        Calendar c1 = (Calendar) c.clone();
        System.out.println(c.get(Calendar.YEAR) + "  " + (c.get(Calendar.MONTH) + 1) + "  " + c.get(Calendar.DAY_OF_MONTH));
        c.add(Calendar.DAY_OF_MONTH, 1);// 给天数加一,看月份是否改变,改变为最后一天
        if (c.get(Calendar.MONTH) != c1.get(Calendar.MONTH)) {
            System.out.println("是最后一天");
        } else {
            System.out.println("不是最后一天");
        }
    }

    @Test
    public void test7() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parse = sdf.parse("2020-02-13 00:00:00");
        System.out.println(parse);

    }
}
