package com.springboot.cal;

import org.junit.Test;

public class MathTest {
    @Test
    public void test(){
        double d = 1.234d;
        int a = -22;
        System.out.println(Math.ceil(d));//返回大于或等于指定数字的最小整数
        System.out.println(Math.floor(d));//返回小于或等于指定数字的最大整数
        System.out.println(Math.abs(a));//取绝对值
    }
}
