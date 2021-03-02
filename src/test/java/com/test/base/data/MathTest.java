package com.test.base.data;

import org.junit.Test;

import java.math.BigDecimal;

public class MathTest {
    @Test
    public void test() {
        double d = 1.234d;
        int a = -22;
        System.out.println(Math.ceil(d));//返回大于或等于指定数字的最小整数
        System.out.println(Math.floor(d));//返回小于或等于指定数字的最大整数
        System.out.println(Math.abs(a));//取绝对值
    }

    /*
        使用bigDecimal可以避免精度问题
     */
    @Test
    public void test2() {
        BigDecimal bigDecimal1 = new BigDecimal("1.0");
        BigDecimal bigDecimal2 = new BigDecimal("0.9");
        System.out.println(bigDecimal1.subtract(bigDecimal2));
    }

    /**
     * 如何去除小数点前两位,并四舍五入.
     */
    @Test
    public void test3() {
        double d = 1256.22d;
        d = d / 100;
        System.out.println(d);//12.5622
        System.out.println(Math.round(d) * 100);//13*100 = 1300
    }

    @Test
    public void integer() {
        int a = 45000;
        System.out.println(a);
    }

    /**
     * 使用 new BigDecimal("string") 形式初始化才正确
     */
    @Test
    public void bigDecimal() {
        //初始化BigDecimal时就不正确没有精确,导致计算不正确
        BigDecimal d1 = new BigDecimal(11.2);
        BigDecimal d2 = new BigDecimal(2);
        //22.399999999999998578914528479799628257751464843750
        System.out.println(d1.multiply(d2));

        //使用 new BigDecimal("string") 形式初始化才正确
        BigDecimal d3 = new BigDecimal("11.2");
        BigDecimal d4 = new BigDecimal("2");
        //22.4
        System.out.println(d3.multiply(d4));

        BigDecimal b = new BigDecimal("1.0");
        BigDecimal c = new BigDecimal("0.1");
        //初始化BigDecimal时就不正确没有精确,导致计算不正确
        BigDecimal d = new BigDecimal(1.0);
        BigDecimal e = new BigDecimal(0.1);
        System.out.println(b.subtract(c));
        System.out.println(d.subtract(e));
    }

    /**
     * double 转 string
     */
    @Test
    public void doubleToString() {
        double d = 0.1;
        System.out.println(String.valueOf(d));
        String t = d + "";
        System.out.println(t);
    }

    /**
     * BigDecimal 如果 除不尽会报错,应该四舍五入处理
     */
    @Test
    public void bigDecimal2() {
        BigDecimal b1 = new BigDecimal("3");
        BigDecimal b2 = new BigDecimal("10");
        //除不尽的时候会报错,应四舍五入
        //java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        //BigDecimal divide = b2.divide(b1);
        BigDecimal divide = b2.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);
    }
}
