package com.test.base.class_relationship.extendtest;

public interface Animal {
    void doSomething();
}

class Dog implements Animal {

    @Override
    public void doSomething() {

    }

    public void wangWang() {

    }
}

class TestDemo {
    /**
     * 实现和继承是默认的向上转型,所以子类的方法不能直接调用
     */
    public static void main(String[] args) {
        Animal dog = new Dog();
//        dog.wangWang();
    }
}
