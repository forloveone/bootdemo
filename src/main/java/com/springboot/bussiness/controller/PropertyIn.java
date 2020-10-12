package com.springboot.bussiness.controller;

import com.springboot.bussiness.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PropertyIn {

    //属性注入
    @Value("${my.name}")
    private String name;

    @Value("${my.test:hello}")
    private String test;

    @Value("${my.test:@hello@health}")
    private String test2;

    //属性注入Pojo
    @Autowired
    private Person person;

    @RequestMapping("/propery")
    public void test() {
//        dujinyue
//        hello
//        @hello@health
        System.out.println("ultra#0831");
        System.out.println(name);
        System.out.println(test);
        System.out.println(test2);
    }
}
