package com.springboot.thread;

import com.springboot.pojo.ThreadTest;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalTest {
    /**
     * ThreadLocal<Map> local = new ThreadLocal<>();
     * 对象中的ThreadLocal,可以为每一个线程分配自己的本地存储
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadTest test = new ThreadTest();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                Map map = new HashMap<>();
                map.put("1","123");
                test.getLocal().set(map);
                Map to = test.getLocal().get();
                System.out.println(to.get("1"));
            }
        };
        t1.start();
        Thread.sleep(5000);
        Map mainMap = test.getLocal().get();
        mainMap.get("1");
    }
}
