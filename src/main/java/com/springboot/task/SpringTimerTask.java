package com.springboot.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring实现的注解,需要xml中配置东西,cron需要进一步了解
 * 还可以用Quartz框架
 * <p>
 * Scheduled(fixedRate = 6000)：上一次开始执行时间点后每隔6秒执行一次。
 * Scheduled(fixedDelay = 6000)：上一次执行完毕时间点之后6秒再执行。
 * Scheduled(initialDelay=1000, fixedRate=6000)：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次。
 * <p>
 * 任务并行和串行
 */
@Component
@Slf4j
public class SpringTimerTask {

//    @Scheduled(cron = "*/5 * * * * ?")
//    public void job() {
//        log.info("");
//        System.out.println("hello");
//    }
//
//    @Scheduled(cron = "*/5 * * * * ?")
//    public void job3() throws InterruptedException {
//        log.info("");
//        System.out.println("hello2");
//        Thread.sleep(100000);
//    }

    //    @Scheduled(fixedRate = 6000)
    public void job2() {
        System.out.println("hahha");
    }
    @Scheduled(cron = "*/5 * * * * ?")
//    @Async
    //SpringBoot项目中，定时任务默认是串行执行的，不论启动多少任务，都是一个执行完成，再执行下一个。
    //设置一个任务并行需要 两个注解一起使用
    // Scheduled Async 这样对同一个定时任务才是并行的,否则只用scheduled是 对同一任务串行执行
    public void bingxign() throws InterruptedException {
        System.out.println("1");
        Thread.sleep(10000);
        System.out.println("2");
    }


}
