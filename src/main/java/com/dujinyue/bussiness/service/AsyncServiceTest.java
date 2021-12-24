package com.dujinyue.bussiness.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceTest {
    @Async
    public void asyncMethodOne(int i) {
        System.out.println("Async one " + i);
    }

    @Async
    public void asyncMethodTwo(int i) {
        System.out.println("Async two " + i);
    }
}
