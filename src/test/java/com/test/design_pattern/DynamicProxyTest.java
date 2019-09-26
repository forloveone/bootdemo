package com.test.design_pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
}

interface Subject {
    void doSomething(String str);
}

class SubjectImpl implements Subject {

    @Override
    public void doSomething(String str) {
        System.out.println("业务逻辑操作" + str);
    }
}

interface IAdvice {
    void exec();
}

class BeforeAdvice implements IAdvice {
    @Override
    public void exec() {
        System.out.println("前置通知执行");
    }
}


class Handler implements InvocationHandler {
    private Object target = null;//被代理对象

    public Handler(Object obj) {
        this.target = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里并不需要做操作,因为被DynamicProxy做了,所以这里是模板代码
        System.out.println("Handler invoke执行");
        return method.invoke(target, args);
    }
}

class DynamicProxy<T> {
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) {
        //寻找JoinPoint连接点,AOP框架使用元数据定义
        if (true) {
            //执行一个前置通知
            (new BeforeAdvice()).exec();
        }
        //执行目标,并返回结果
        //重新生成了一个对象
        return (T) Proxy.newProxyInstance(loader, interfaces, h);//这个是java API
    }
}

class SubjectDynamicProxy extends DynamicProxy{
    public static<T> T newProxyInstance(Subject subject){
        ClassLoader classLoader = subject.getClass().getClassLoader();
        Class<?>[] interfaces = subject.getClass().getInterfaces();
        //适用这样的好处是消除了模板代码
        InvocationHandler handler = new Handler(subject);
        return newProxyInstance(classLoader,interfaces,handler);
    }
}

class ClientTest{
    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
//        Handler handler = new Handler(subject);
//        Subject proxy = DynamicProxy.newProxyInstance(subject.getClass().getClassLoader(),subject.getClass().getInterfaces(),handler);
        Subject proxy =SubjectDynamicProxy.newProxyInstance(subject);
        proxy.doSomething("helllo 动态代理");
        //动态代理,就是实现了不改变已有代码结构的情况下增强或控制对象的行为.
    }
}

