package com.test.question;

public class Base {
    public String name = "base";

    public String getName() {
        return name;
    }

    public Base() {
        tellName1();
        //因为printName方法被重载了,所以会调用重载的方法,但是这时候子类的属性并没有初始化,所以 为null
        printName();
        System.out.println(name);
    }

    public void tellName1() {
        System.out.println("Base tell name " + name);
    }

    public void printName() {
        System.out.println("Base print name " + name);
    }
}

class David extends Base {
    public String name = "David";
    public String getName() {
        return name;
    }
    public David() {
        tellName();
        printName();
    }

    public void tellName() {
        System.out.println("David tell name " + name);
    }

    public void printName() {
        System.out.println("David print name " + name);
    }
}

class TestTemp{
    public static void main(String[] args) {
        Base david = new David();
        David david1 = (David) david;
        System.out.println();
        System.out.println(david.getName());
        System.out.println(david1.getName());
        System.out.println();
        System.out.println(david.name);
        System.out.println(david1.name);
    }
}
