package com.springboot.thread;

/**
 * 简单的创建线程并启动
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("pong");
                }
            }
        };
        t.start();
        Thread.sleep(5000);
        System.out.println("ping");
    }
}
