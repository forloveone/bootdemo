package com.test.class_relationship;

/**
 * 闭包是一个可调用的对象,它记录了一些信息,这些信息来至于创建它的作用域
 * 闭包的价值在于可以作为函数对象或者匿名函数，持有上下文数据，作为第一级对象进行传递和保存。闭包广泛用于回调函数、函数式编程中
 */
public class BiBao {
    private int length = 0;

    //private|public
    private class InnerClass implements ILog {
        @Override
        public void write(String message) {
            length = message.length();
            System.out.println("DemoClass1.InnerClass:" + length);
        }
    }

    public ILog logger() {
        return new InnerClass();
    }

    public static void main(String[] args) {
        BiBao demoClass1 = new BiBao();
        demoClass1.logger().write("abc");

        //.new
        BiBao dc1 = new BiBao();
        InnerClass ic = dc1.new InnerClass();
        ic.write("abcde");
    }
}

interface ILog {
    void write(String message);
}

//-------------------------------------
//回调
interface Incr {
    void incr();
}

class callee1 implements Incr {
    private int i = 0;

    @Override
    public void incr() {
        i++;
        System.out.println(i);
    }
}

class MyIncr {
    public void incr() {
        System.out.println("operation");
    }

    static void f(MyIncr incr) {
        incr.incr();
    }
}

class callee2 extends MyIncr {
    private int i = 0;

    public void incr() {
        super.incr();
        i++;
        System.out.println(i);
    }

    private class Closure implements Incr{

        @Override
        public void incr() {
            callee2.this.incr();
        }
    }

    Incr getCallBackReference(){
        return new Closure();
    }
}

class Caller{
    private Incr callbakc;
    Caller(Incr incr){
        callbakc = incr;
    }
    void go(){
        callbakc.incr();
    }
}

class TestDemo2{


    public static void main(String[] args) {
        callee1 c123 = new callee1();
        callee2 c321 = new callee2();
        MyIncr.f(c321);
        Caller caller1 = new Caller(c123);
        Caller caller2 = new Caller(c321.getCallBackReference());
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
}