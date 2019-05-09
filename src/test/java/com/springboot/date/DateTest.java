package com.springboot.date;

import com.springboot.utils.DateUtil;
import org.junit.Test;

import java.text.ParseException;

public class DateTest {
    @Test
    public void test() throws ParseException {
        System.out.println(DateUtil.convertTimeToLong("1990-01-27"));;
        long d = DateUtil.parseIdCardForBirthdayMilli("410504199001270513");
        System.out.println(d);

        int age = DateUtil.parseIdCardforAge("410504199001270513");
        System.out.println(age);
    }
}
