package com.test.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 * 	闭锁相当于一扇门:
 * 		在闭锁到达结束状态之前,这扇门一直是关闭的,并且没有任何线程能通过.当达到结束状态时,这扇门会打开并允许所有的线程通过.
 * 闭锁达到结束状态后,状态不能再改变,会永远保持打开状态.
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch count = new CountDownLatch(2);
//        CountDownLatch可以唤起多个任务,CountDownLatch不可重用，计数值为0该CountDownLatch就不可再用了
        Boss4CountDownLatch boss = new Boss4CountDownLatch(count, "boss");
        Boss4CountDownLatch bossWife = new Boss4CountDownLatch(count, "boss wife");
        bossWife.start();
        boss.start();
        //CountDownLatch线程运行到某个点上之后，只是给某个数值-1而已，该线程继续运行
        Worker4CountDownLatch wor1 = new Worker4CountDownLatch(count, "work1");
        Worker4CountDownLatch wor2 = new Worker4CountDownLatch(count, "work2");
        wor1.start();
        wor2.start();
    }

    private static class Boss4CountDownLatch extends Thread {
        private CountDownLatch countDownLatch;

        public Boss4CountDownLatch(CountDownLatch countDownLatch, String name) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "开始等待工人完成");
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName() + "开始检查");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class Worker4CountDownLatch extends Thread {
        private CountDownLatch countDownLatch;

        public Worker4CountDownLatch(CountDownLatch countDownLatch, String name) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "开始工作");
                // 工作线程开始处理，这里用Thread.sleep()来模拟业务处理
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "工作完毕");
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
