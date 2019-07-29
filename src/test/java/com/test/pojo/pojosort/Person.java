package com.test.pojo.pojosort;


import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 要实现javaPojo对象的排序(使用java提供的工具类)
 * 方式一:实现Comparable接口,实现CompareTo方法
 * 方式二:自定义比较器,实现Comparator接口,实现Compare方法
 *
 *         Collections.sort(personList);
 *
 *  推荐使用第二种 可以直接使用sort方法 而不用Collections的方法,而且不用重写许多方法 更简洁
 */
@Data
public class Person implements Comparable<Person> {
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return age == person.age;
    }

    @Override
    public int hashCode() {
        return age;
    }

    /**
     * o 表示要比较的对象
     * 负整数、零或正整数，根据此对象是小于、等于还是大于指定对象。
     */
    @Override
    public int compareTo(Person o) {
        if (o.age > this.age) {
            return -1;
        } else if (o.age < this.age) {
            return 1;
        } else {
            return 0;
        }
    }

    @Test
    public void test() {
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person();
        p1.setAge(10);
        Person p2 = new Person();
        p2.setAge(11);
        Person p3 = new Person();
        p3.setAge(6);
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);

        System.out.println(p1.compareTo(p2));
        System.out.println(p1.compareTo(p3));
        System.out.println(p3.compareTo(p1));
        Collections.sort(personList);
        System.out.println(personList);
    }
}
