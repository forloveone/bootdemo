package com.test.thread;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 * 信号量,限制某段代码块的并发数
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        //一个许可 可以产生synchronized 同步效果
        Semaphore semaphore = new Semaphore(2);
        SemaphoreThread semaphoreThread = new SemaphoreThread(semaphore, "thread1");
        SemaphoreThread2 semaphoreThread2 = new SemaphoreThread2(semaphore, "thread2");
        semaphoreThread.start();
        semaphoreThread2.start();
    }

    private static class SemaphoreThread extends Thread {
        private Semaphore semaphore;

        public SemaphoreThread(Semaphore semaphore, String name) {
            super(name);
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            //不是阻塞方式的到许可
//            boolean b = semaphore.tryAcquire();
//            System.out.println(semaphore.availablePermits());
            try {
                //阻塞到得到许可
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (b) {
            semaphoreTest(getName());
            //释放许可
            semaphore.release();

//            }
        }
    }

    private static class SemaphoreThread2 extends Thread {
        private Semaphore semaphore;

        public SemaphoreThread2(Semaphore semaphore, String name) {
            super(name);
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
//            boolean b = semaphore.tryAcquire();
//            System.out.println(semaphore.availablePermits());
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (b) {
            semaphoreTest(getName());
            semaphore.release();
//            }
        }
    }

    public static void semaphoreTest(String name) {
        for (int i = 0; i < 100; i++) {
            System.out.println(name + "  " + i);
        }
    }
}
