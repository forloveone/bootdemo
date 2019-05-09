package com.springboot.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring实现的注解,需要xml中配置东西,cron需要进一步了解
 * 还可以用Quartz框架
 *
 * Scheduled(fixedRate = 6000)：上一次开始执行时间点后每隔6秒执行一次。
 * Scheduled(fixedDelay = 6000)：上一次执行完毕时间点之后6秒再执行。
 * Scheduled(initialDelay=1000, fixedRate=6000)：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次。
 */
@Component
@Slf4j
public class SpringTimerTask {

//    @Scheduled(cron = "*/5 * * * * ?")
    public void job(){
        log.info("");
        System.out.println("hello");
    }

//    @Scheduled(fixedRate = 6000)
    public void job2(){
        System.out.println("hahha");
    }
}
