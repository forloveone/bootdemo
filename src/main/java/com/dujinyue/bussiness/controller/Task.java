package com.dujinyue.bussiness.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * spring实现的注解,需要xml中配置东西
 * 还可以用Quartz框架
 */
@Slf4j
@Component
public class Task {

    //    @Scheduled(cron = "*/5 * * * * ?")
    public void job1() throws InterruptedException {
        log.info("");
        System.out.println("hello2");
        Thread.sleep(100000);
    }

    //    @Scheduled(fixedRate = 6000) //上一次开始执行时间点后每隔6秒执行一次
    //@Scheduled(fixedDelay = 6000) //上一次执行完毕时间点之后6秒再执行。
    //@Scheduled(initialDelay=1000, fixedRate=6000) //第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次。
    public void job2() {
        System.out.println("hahha");
    }

    /**
     * 报错也不会影响下次调度
     *
     * @throws Exception
     */
//    @Scheduled(cron = "*/5 * * * * ?")
    public void job3() throws Exception {
        log.info("");
        System.out.println("hello2");
        throw new Exception();
    }

    //    @Scheduled(cron = "*/5 * * * * ?")
//    @Async
    //SpringBoot项目中，定时任务默认是串行执行的，不论启动多少任务，都是一个执行完成，再执行下一个。
    //设置一个任务并行需要 两个注解一起使用
    // Scheduled Async 这样对同一个定时任务才是并行的,否则只用scheduled是 对同一任务串行执行
    public void bingxign() throws InterruptedException {
        System.out.println("1");
        Thread.sleep(10000);
        System.out.println("2");
    }


    public void test1() {
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

    public void test2() {
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
