package com.springboot.bussiness.controller;

import com.springboot.aop.AopBeforeAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class Aop {

    //produces 可以定制返回的response的媒体类型和字符集
    @RequestMapping(value = "/", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
//    //如果两种注解同时存在都会执行
//    @AopBeforeAnnotation(name = "基于注解的aop")
    public String home() {
        System.out.println("test home aop");
        return "Hello World!";
    }

    @AopBeforeAnnotation(name = "基于注解的aop 2")
    @RequestMapping(value = "/annotation", method = RequestMethod.POST)
    public void annotation() {
        System.out.println("test annotation aop");
    }
}
