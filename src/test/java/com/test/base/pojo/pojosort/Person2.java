package com.test.base.pojo.pojosort;

import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person2 {
    private int age;

    private List<Person2> person2List = new ArrayList<>();

    @Before
    public void init() {
        Person2 p = new Person2();
        p.setAge(10);
        Person2 p2 = new Person2();
        p2.setAge(11);
        Person2 p3 = new Person2();
        p3.setAge(6);

        person2List.add(p);
        person2List.add(p2);
        person2List.add(p3);

        boolean equals = p.equals(p2);
        System.out.println(equals);
    }

    @Test
    public void test() {
        person2List.sort(new ComparePersonByAgeAsc());
        System.out.println(person2List);
    }
}
