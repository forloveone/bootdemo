package com.test.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏
 * CyclicBarrier的某个线程运行到某个点上之后，该线程即停止运行，直到所有的线程都到达了这个点，所有线程才重新运行；
 * CountDownLatch则不是，某线程运行到某个点上之后，只是给某个数值-1而已，该线程继续运行
 * CyclicBarrier只能唤起一个任务，CountDownLatch可以唤起多个任务
 * CyclicBarrier可重用，CountDownLatch不可重用，计数值为0该CountDownLatch就不可再用了
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        //CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，
        // 每个线程使用await()方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。
        //CyclicBarrier的另一个构造函数CyclicBarrier(int parties, Runnable barrierAction)，
        // 用于线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景。
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        });
        for (int i = 0; i < 3; i++) {
            Worker worker = new Worker(cyclicBarrier);
            worker.start();
        }
    }

    // 自定义工作线程
    private static class Worker extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Worker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "开始执行");
                // 工作线程开始处理，这里用Thread.sleep()来模拟业务处理
//                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


