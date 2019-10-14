package com.test.thread;

/**
 * this引用溢出,在构造方法,显示的传递给另一个线程,并启动线程,导致this被启动线程得到,但是对象还没有初始化完毕.
 */
public class ThisOut {
    public static void main(String[] args) throws InterruptedException {
        ThisEscape escape = new ThisEscape(new EventSource() {
            @Override
            public void registerListener(EventListener e) {
                System.out.println("123");
            }
        });
    }
}

class ThisEscape {
    public ThisEscape(EventSource source) throws InterruptedException {
        ThisEscape escape = this;
        Thread thread = new MyThread(escape);
        thread.start();
        Thread.sleep(10000);
        source.registerListener(new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }
        });
        a = 111;
    }

    void doSomething(Event e) {
        System.out.println(a);
    }

    public int a;

}
class MyThread extends Thread{
    public MyThread(ThisEscape escape) {
        this.escape = escape;
    }
    private ThisEscape escape;

    @Override
    public void run() {
        escape.doSomething(new Event() {
        });
    }

}

interface EventSource {
    void registerListener(EventListener e);
}

interface EventListener {
    void onEvent(Event e);
}

interface Event {
}
