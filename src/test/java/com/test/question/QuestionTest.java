package com.test.question;

import org.junit.Test;

public class QuestionTest {
    private static void testMe() {
        System.out.println("testMe");
    }

    private void testMe2() {
        System.out.println("testMe2");
    }

    /**
     * null可以被强制类型转换成任意类型的对象，通过这样的方式可以执行对象的静态方法
     */
    @Test
    public void NULLTest() {
        ((QuestionTest) null).testMe();
        ((QuestionTest) null).testMe2();
    }

    /**
     * 变量初始化
     */
    static String s;

    @Test
    public void param() {
        System.out.println("S = " + s);
        //S = null
    }
}
