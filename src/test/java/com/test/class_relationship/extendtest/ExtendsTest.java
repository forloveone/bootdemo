package com.test.class_relationship.extendtest;

import org.junit.Test;

/**
 * 继承关系的缺点
 * test方法, 就是如果有3级继承关系,A中有方法test,被C中使用了,有一天B需要重写test方法,就会导致C中test方法行为会变化.
 */
public class ExtendsTest {
    class A {
        public String name = "nameA";

        void doA() {
            System.out.println("A");
        }

        void test() {
            System.out.println("test A");
        }
    }

    class B extends A {
        public String name = "nameB";

        void doA() {
            System.out.println("B " + name);
        }

//    void test(){
//        System.out.println("test B");
//    }
    }

    class C extends B {
        void doA() {
            System.out.println("C");
            test();
        }
    }

    @Test
    public void test() {
        A a = new A();
        A c = new C();
        A b = new B();
        a.doA();
        b.doA();
        c.doA();
    }
//--------------------------------------------
    class PersonFather {
        private String name = "person";

        public String getName() {
            return name;
        }
    }

    class PersonSon extends PersonFather {
        private String name = "person son";
//    public String getName() {
//        return name;
//    }
    }

    @Test
    public void test2() {
        PersonSon son = new PersonSon();
        System.out.println(son.getName());//person
    }
}
