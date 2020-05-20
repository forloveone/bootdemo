package com.test.class_relationship;

public class PersonTest {
    private String name = "person";

    public String getName() {
        return name;
    }
}

class PersonSon extends PersonTest {
    private String name = "person son";
//    public String getName() {
//        return name;
//    }
}

class TestMain {
    /**
     *
     */
    public static void main(String[] args) {
        PersonSon son = new PersonSon();
        System.out.println(son.getName());//person
    }
}
