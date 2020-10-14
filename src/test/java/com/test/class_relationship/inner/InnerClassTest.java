package com.test.class_relationship.inner;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.CharBuffer;

/**
 * 内部类可以继承其它类,也可以实现接口
 */
public class InnerClassTest {
    private class Temp implements Serializable {

    }

    private class Temp2 extends File {
        public Temp2(String pathname) {
            super(pathname);
        }
    }
}

/**
 * java的多继承是继承一个类,实现多个接口(是一种特殊实现),内部类可以真正做到多继承
 * 内部类,配合接口,抽象类实现多继承,内部类允许继承多个非接口类型
 * 内部类使得多重继承的解决方案变得完整
 */
class Outer {
    private String name;

    // 1.成员内部类
    private class Inner {
        String name;
        String address;
    }

    // 2.静态内部类
    private static class Inner2 implements Readable {
        @Override
        public int read(CharBuffer cb) throws IOException {

            return 0;
        }
    }

    // 3.局部内部类,不能使用权限修饰符
    public void test() {
        class Inner3 implements Runnable {
            @Override
            public void run() {

            }
        }
    }
}
