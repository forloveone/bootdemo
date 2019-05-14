package com.springboot.events;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 * 可以做Bean与Bean之间的消息通讯
 */
@Data
public class EventsTest extends ApplicationEvent {

    private String message;

    /**
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public EventsTest(Object source,String message) {
        super(source);
        this.message = message;
    }
}
