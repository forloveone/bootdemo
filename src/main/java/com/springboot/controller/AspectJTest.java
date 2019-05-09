package com.springboot.controller;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectJTest {

    //TODO execution 详解 复杂的aop能实现什么?
    @Pointcut("execution(* home(..))")
    private void pointCut(){ }

    @Before("pointCut()")
    public void before(){
        System.out.println("aop before test success!");
    }

}
