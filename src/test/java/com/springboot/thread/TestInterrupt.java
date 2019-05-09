package com.springboot.thread;

public class TestInterrupt {
    private static int a;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0;i<1000000000;i++){
                    a++;
                }
            }
        };
        t.start();
//        t.interrupt();
//        System.out.println(t.isInterrupted());
        t.join();//Waits for this thread to die.
        System.out.println(t.isAlive());
        //Thread.sleep(10); 使当前线程休眠,针对这个例子是主线程
        System.out.println(a);
    }
}
