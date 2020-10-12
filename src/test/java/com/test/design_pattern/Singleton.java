package com.test.design_pattern;

public class Singleton {
    private Singleton(){}
    private static Singleton singleton = new Singleton();
    public static Singleton getInstance(){
        return singleton;
    }
    //类中其他方法尽可能是static的
}