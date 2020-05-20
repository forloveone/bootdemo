package com.springboot.task;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * java原生的定时任务
 */
public class TimerTest {


    public void test() {
        //test注解无法测试定时任务...
    }

    public static void main(String[] args) {
//        test1();
//        test2();
    }

    public static void test1() {
        Timer timer = new Timer();
        //时刻表按固定率
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // task to run goes here
                System.out.println("tt");
            }
        }, 3000, 5000);
    }

    public static void test2() {
        Runnable runnable = new Runnable() {
            public void run() {
                // task to run goes here
                System.out.println("Hello !!");
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 10, 5, TimeUnit.SECONDS);
    }
}
