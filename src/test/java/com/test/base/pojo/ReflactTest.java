package com.test.base.pojo;

public class ReflactTest {
    public void test(){
        System.out.println("反射调用成功");
    }

    public static void staticMethod(String txt){
        System.out.println("反射调用静态方法成功"+txt);
    }
}
