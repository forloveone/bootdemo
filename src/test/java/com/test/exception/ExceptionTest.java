package com.test.exception;

import org.junit.Test;

public class ExceptionTest {
    /**
     * 	不能在finally 块中使用return,finally 块中的return 返回后方法结束执行，不会再执行try 块中的return 语句。
     */
    @Test
    public void testTryCatchFinally(){
        String temp = test();
        System.out.println(temp);
    }

    private String test(){
        try {
            return "nomal";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "finally";
        }
    }
}
