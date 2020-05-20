package com.test.class_relationship;

/**
 * 继承关系的缺点
 * test方法, 就是如果有3级继承关系,A中有方法test,被C中适用了,有一天B需要重写test方法,就会导致C中test方法行为会变化.
 */
public class ExtendTest {
    public static void main(String[] args) {
        A a = new A();
        A c = new C();
        A b = new B();
        a.doA();
        b.doA();
        c.doA();
    }
}

class A {
    public String name = "nameA";
    void doA(){
        System.out.println("A");
    }

    void test(){
        System.out.println("test A");
    }
}

class B extends A{
    public String name = "nameB";
    void doA(){
        System.out.println("B");
        System.out.println(name);
    }

//    void test(){
//        System.out.println("test B");
//    }
}

class C extends B{
    void doA(){
        System.out.println("C");
        test();
    }
}
