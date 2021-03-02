package com.test.base.class_relationship.inner;

/**
 * 在JAVA中，闭包是通过“接口+内部类”实现，JAVA的内部类也可以有匿名内部类。
 * 闭包是一个可调用的对象,它记录了一些信息,这些信息来至于创建它的作用域
 * 闭包的价值在于可以作为函数对象或者匿名函数，持有上下文数据，作为第一级对象进行传递和保存。闭包广泛用于回调函数、函数式编程中
 */

/**
 * 在Java中，可以将一个类定义在另一个类里面或者一个方法里面，这样的类称为内部类。广泛意义上的内部类一般来说包括这四种：
 * 成员内部类、局部内部类、匿名内部类和静态内部类。下面就先来了解一下这四种内部类的用法。
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