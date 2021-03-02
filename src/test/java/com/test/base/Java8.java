package com.test.base;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Java8 {
    //重复注解
    //Java 8拓宽了注解的应用场景。现在，注解几乎可以使用在任何元素上：局部变量、接口类型、超类和接口实现类，甚至可以用在函数的异常定义上

    /**
     * 函数式编程
     * lambda 表达式 参数列表 箭头 函数体组成
     */
    @Test
    public void testLambda() {
        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
        Arrays.asList("a", "b", "d").forEach(System.out::println);

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return a.compareTo(b);
//            }
//        });
        System.out.println(names);

//        Collections.sort(names, (a, b) -> a.compareTo(b));

        names.sort(String::compareTo);
        System.out.println(names);
    }

    //接口中可以添加默认方法,使得实现类不必非得实现它
    //可以定义静态方法
    private interface Defaulable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }

        static void staticMethod() {

        }
    }

    /**
     * Optional仅仅是一个容易：存放T类型的值或者null。它提供了一些有用的接口来避免显式的null检查
     */
    @Test
    public void nullTest() {
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set? " + fullName.isPresent());
        System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }

    /**
     * Steam API极大得简化了集合操作
     */
    @Test
    public void streamApi(){

    }
}
