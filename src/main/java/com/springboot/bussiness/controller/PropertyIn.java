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

    //属性注入Pojo
    @Autowired
    private Person person;

    @RequestMapping("/propery")
    public void test() {
        System.out.println(name);
    }
}
