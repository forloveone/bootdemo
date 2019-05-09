package com.springboot.cal;

import org.junit.Test;

import java.math.BigDecimal;

//两个double相乘为什么数值不正确
//已经修改正确
public class DoubleTest {

    @Test
    public void test() {
        double dd = doubleTest(11.2, 2);//11.2*11.2=125.43999999999998
        System.out.println(dd);
    }

    private double doubleTest(double d, int x) {
        BigDecimal s = new BigDecimal(String.valueOf(d));
        for (int i = 1; i < x; i++) {
            s = s.multiply(s);
        }
        return s.doubleValue();
    }

    /**
     * BigDecimal
     */
    @Test
    public void bigDecimal(){
        BigDecimal b = new BigDecimal("1.0");
        BigDecimal c = new BigDecimal("0.1");
        BigDecimal d = new BigDecimal(1.0);
        BigDecimal e = new BigDecimal(0.1);//初始化BigDecimal时就不正确没有精确,导致计算不正确
        System.out.println(b.subtract(c));
        System.out.println(d.subtract(e));
    }

    /**
     * double 转 string
     */
    @Test
    public void doubleToString(){
        double d = 0.1;
        System.out.println(String.valueOf(d));
        String t = d +"";
        System.out.println(t);
    }
}
