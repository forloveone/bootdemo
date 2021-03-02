package com.test.base.class_relationship;


//回调

/**
 * 回调接口，定义回调中会被执行的方法
 */
interface MyCallback {
    void func();
}

/**
 * 调用者，预留回调接口
 */
class Caller {

    private MyCallback myCallback;

    public void doCall() {
        // 做自己的业务
        System.out.println("doCall");
        //触发回调
        myCallback.func();
    }

    //设置回调者
    public void setMyCallback(MyCallback myCallback) {
        this.myCallback = myCallback;
    }

}

/**
 * 注册函数
 */
class CallBackTest {
    public static void main(String[] args) {
        Caller caller = new Caller();
        //实例化具体回调函数，实现回调方法
        caller.setMyCallback(new MyCallback() {
            @Override
            public void func() {
                System.out.println("Hello world");
            }
        });
        caller.doCall();
    }
}
