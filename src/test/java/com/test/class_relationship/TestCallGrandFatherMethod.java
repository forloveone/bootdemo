package com.test.class_relationship;

import org.junit.Test;

/**
 * 访问祖类方法 GrandFather
 */
public class TestCallGrandFatherMethod {
    class GrandFather {
        void think() throws IllegalAccessException, InstantiationException {
            System.out.println("grandFather think");
        }
    }

    class Father extends GrandFather {
        @Override
        void think() throws IllegalAccessException, InstantiationException {
            System.out.println("Father think");
        }
    }

    class Son extends Father {
        @Override
        void think() throws IllegalAccessException, InstantiationException {
            System.out.println("Son think");
            super.think();
            new GrandFather().think();
        }
    }

    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        Son son = new Son();
        son.think();
        //GrandFather.class.newInstance().think();
    }
}
