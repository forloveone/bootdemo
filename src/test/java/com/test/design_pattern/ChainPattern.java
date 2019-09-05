package com.test.design_pattern;

/**
 * 责任链模式
 * 一个请求,能被多个对象处理
 * 原本想象观察者模式一样使用List,但是如果每个对象的类型不同,处理方法名称不同,那么没有办法,必须知道每个对象的具体逻辑,这个导致chain很复杂,耦合会非常深
 * <p>
 * 而且观察者模式,定义是对复杂依赖的管理(类似触发链,都能作为被观察者和观察者,形成大网这只是比喻,不能成网会循环调用),责任链则是定义链.
 * <p>
 * 所以简单优雅的来.
 */
public class ChainPattern {
    /**
     * 一般会把链的组合给封装起来,直接返回第一个请求的结果,这样更优雅
     */
    public static void main(String[] args) {
        //一个请求
        RequestTest requestTest = new RequestTest();
        //链
        Chain chain = new HandlerTest();
        Chain chain2 = new HandlerTest2();
        chain.setNextChain(chain2);

        //请求过链
        chain.handler(requestTest);
    }
}

/**
 * 接受请求并处理
 */
abstract class Chain {
    private Chain nextChain;

    /**
     * 请求的处理方法
     * 请求的参数可以是一个接口,可以做到更灵活和解耦合.
     */
    public final void handler(RequestTest requestTest) {
        if (requestTest.getA() == getHandlerLevel()) {
            this.doRequest(requestTest);
        } else {
            //不属于自己的处理级别
            //有下一个处理者
            if (nextChain != null) {
                nextChain.handler(requestTest);
            } else {
                //没有处理者
                System.out.println("请求没有被处理");
            }
        }
    }

    /**
     * 具体的处理任务
     * 这个可以返回一个对象,这里简单的用void模拟了,对象可以更好的返回信息
     * 甚至是一个Response的接口,这样可以跟灵活
     */
    abstract void doRequest(RequestTest requestTest);

    /**
     * 定义自己的处理级别
     * 这里的级别可以是一个对象,判断时可以用equals来判断 (需要重写对象的hashcode和equals方法)
     */
    abstract int getHandlerLevel();

    /**
     * 链的编排方法
     */
    protected void setNextChain(Chain chain) {
        nextChain = chain;
    }
}

class HandlerTest extends Chain {

    @Override
    void doRequest(RequestTest requestTest) {
        System.out.println("接受到请求,并处理处理级别为" + getHandlerLevel());
    }

    @Override
    int getHandlerLevel() {
        return 2;
    }
}

class HandlerTest2 extends Chain {

    @Override
    void doRequest(RequestTest requestTest) {
        System.out.println("接受到请求,并处理处理级别为" + getHandlerLevel());
    }

    @Override
    int getHandlerLevel() {
        return 1;
    }
}

//请求
class RequestTest {
    private int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
