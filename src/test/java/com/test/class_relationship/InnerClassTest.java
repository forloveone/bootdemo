package com.test.class_relationship;

/**
 * 内部类可以继承其它类,也可以实现接口
 */
public class InnerClassTest {
    private class Temp implements InterfaceTest {

        @Override
        public void test1() {

        }

        @Override
        public void test2() {

        }
    }

    private class Temp2 extends JsonS {

    }
}
