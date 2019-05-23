package com.springboot.cal;

import org.junit.Test;

import java.math.BigDecimal;

public class MathTest {
    @Test
    public void test(){
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
    public void test2(){
        BigDecimal bigDecimal1 = new BigDecimal("1.0");
        BigDecimal bigDecimal2 = new BigDecimal("0.9");
        System.out.println(bigDecimal1.subtract(bigDecimal2));
    }

    /**
     * 如何去除小数点前两位,并四舍五入.
     */
    @Test
    public void test3(){
        double d = 1256.22d;
        d = d/100;
        System.out.println(d);//12.5622
        System.out.println(Math.round(d)*100);//13*100 = 1300
    }
}
