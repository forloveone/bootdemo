package com.test.base.class_relationship.extendtest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 父类
 */
class Father {
    public Father() {
        super();
//        System.out.println("father create " + System.identityHashCode(this));
    }

    public void fatherSay() {
        System.out.println("fatherSay");
    }

    public void doSomeThing(HashMap hashMap) {
        System.out.println("父类doSomeThing方法执行");
    }

    public void doSomeThing2(Map map) {
        System.out.println("父类doSomeThing2方法执行");
    }
}

/**
 * 子类
 */
class Son extends Father {
    public Son() {
        super();
//        System.out.println("son create " + System.identityHashCode(this));
    }

    //子类入参放大了,这是重载(和父类的doSomeThing方法重载)不是覆写
    public void doSomeThing(Map map) {
        System.out.println("子类doSomeThing方法执行");
    }

    public void doSomeThing2(HashMap hashMap) {
        System.out.println("子类doSomeThing2方法执行");
    }
    //这才是复写
//    public void doSomeThing2(Map map){
//        System.out.println("父类doSomeThing2方法执行");
//    }
}

public class ExtendsOverloadTest {

    /**
     * 单独创建父类和子类创建的父类不是同一个对象
     */
    @Test
    public void relationShipTest() {
        Father father = new Father();
        Son son = new Son();
    }

    /**
     * 里氏替换
     */
    @Test
    public void lishitihuan() {
        Father f = new Father();
        f.doSomeThing(new HashMap());

        Son son = new Son();
        son.doSomeThing(new HashMap());
        //以上都是 父类doSomeThing方法执行,子类的方法永远不被执行,因为实际上,都是在调用父类的doSomeThing
        System.out.println("---------------");


        Father f2 = new Father();
        f2.doSomeThing2(new HashMap());

        Son son2 = new Son();
        son2.doSomeThing2(new HashMap());
        //上边这种情况,子类在没有覆写父类的前提下,子类方法被执行了,这会引起业务逻辑混乱,所以
        //子类中方法(非覆写)的前置条件必须与超类中同名方法的前置条件相同或者更宽松.
    }

}
