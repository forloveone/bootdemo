package com.test.class_relationship;

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

    public static void main(String[] args) {
        Animal dog = new Dog();
//        dog.wangWang();
    }
}
