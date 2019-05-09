package com.springboot.design_pattern;

/**
 * 单例模式
 * 私有化构造方法,提供方法返回一个唯一实例
 * 私有之后这个构造方法就只能在本类被调用，其它类无法访问，无法调用构造方法也就无法在这个类之外创建对象
 */
public class Singleton {
    private Singleton(){}

    private static Singleton singleton = new Singleton();

    public static Singleton getInstance(){
        return singleton;
    }
}

class Test{
    public static void main(String[] args) {
        Singleton singleton =  Singleton.getInstance();
        Singleton singleton2 =  Singleton.getInstance();
        Singleton singleton3 =  Singleton.getInstance();
        System.out.println(singleton.hashCode());
        System.out.println(singleton2.hashCode());
        System.out.println(singleton3.hashCode());
        // 三个hash值一样证明是单例
    }
}
