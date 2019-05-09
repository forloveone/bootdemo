package com.springboot.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectJTest {

    @Pointcut("execution(* home(..))")
    private void pointCut(){ }

    @Before("pointCut()")
    public void before(){
        System.out.println("aop before test success!");
    }
}
