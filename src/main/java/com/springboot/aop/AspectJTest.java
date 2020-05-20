package com.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 *  Before 可以做参数入参拦截
 *      拦截后可以做什么?
 */
@Aspect
@Component
public class AspectJTest {
    //基于方法规则的拦截
    @Pointcut("execution(* home(..))")
    private void pointCut() {
    }

    //基于注解的拦截   可以很好的控制拦截的粒度,想拦截那个方法就加注解
    @Pointcut("@annotation(com.springboot.aop.AopBeforeAnnotation)")
    private void pointAnnotationCut() {
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("method aop before test success!");
    }

    @Before("pointAnnotationCut()")
    public void annotation(JoinPoint point) {
        //aop 获得入参参数
        Object[] args = point.getArgs();
//        String s = ((Person) args[0]).toString();

        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        //获得方法的参数信息
        Parameter[] parameters = method.getParameters();
        //通过方法获得方法上的注解
        AopBeforeAnnotation annotation = method.getAnnotation(AopBeforeAnnotation.class);
        System.out.println("annotation apo before test "+ method.getName()+" "+annotation.name());
    }
}
