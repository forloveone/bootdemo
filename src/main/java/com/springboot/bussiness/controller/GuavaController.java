package com.springboot.bussiness.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RateLimiter 可以做限流 令牌桶算法
 */
@RestController
public class GuavaController {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    //每秒放两个令牌 ,每秒不超过create/acquire(n)个请求被提交 acquire() 默认为1,
    private static final RateLimiter rateLimiter = RateLimiter.create(2);

    @RequestMapping("/try")
    public String hello() {
        if (rateLimiter.tryAcquire()) { //  一次拿1个
            System.out.println(sdf.format(new Date()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("limit");
        }
        return "hello";
    }

    /**
     * acquire拿不到就等待，拿到为止
     */
    @RequestMapping("/sayHi")
    public String sayHi() {
        System.out.println(System.currentTimeMillis() + "   " + rateLimiter.acquire(6));
        return "hi";
    }
}
