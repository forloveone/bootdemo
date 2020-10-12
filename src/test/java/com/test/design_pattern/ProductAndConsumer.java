package com.test.design_pattern;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 生产者和消费者 通过阻塞队列来通信
 * put 和 take会一直阻塞
 * https://www.cnblogs.com/bjxq-cs88/p/9759571.html
 */
public class ProductAndConsumer {
    private static ArrayBlockingQueue blockQueue = new ArrayBlockingQueue(100);

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        thread1.setName("product");
        thread1.start();
        Thread2 thread2 = new Thread2();
        thread2.setName("cunsumer");
        thread2.start();
    }

    private static class Thread1 extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 1001; i++) {
                try {
                    blockQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Thread2 extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 1001; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " " + blockQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
