package com.dujinyue.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器,监听到特定事件后做处理
 */
@Component
public class TestListener implements ApplicationListener<EventsTest> {
    @Override
    public void onApplicationEvent(EventsTest event) {
        //监听到事件后做处理
        System.out.println(event.getMessage());
    }
}
