package com.test.question;

public class TestClass {
    private static void testMe() {
        System.out.println("testMe");
    }

    private void testMe2() {
        System.out.println("testMe2");
    }

    /**
     * null可以被强制类型转换成任意类型的对象，通过这样的方式可以执行对象的静态方法
     */
    public static void main(String[] args) {
        ((TestClass) null).testMe();
        ((TestClass) null).testMe2();
    }
}
