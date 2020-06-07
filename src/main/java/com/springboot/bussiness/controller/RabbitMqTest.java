package com.springboot.bussiness.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 测试rabbit mq 功能
 */
@Controller
@RequestMapping("/mq")
public class RabbitMqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 生产者发送一个简单消息到,一个队列
     */
    @RequestMapping("serderToRabbitMQ")
    public void sendMQ() {
        String context = "hello " + new Date();
        //单生产者 queue
        rabbitTemplate.convertAndSend("hello", context);
    }

    //多个生产者会 自动分发
    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void reciver(String data) {
        System.out.println("reciver1 " + data);
    }

    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void reciver2(String data) {
        System.out.println("reciver2 " + data);
    }

    //________________________________________________________________

    //rebbitmq topic
    @RequestMapping("/topicSend")
    public void topicSend() {
        rabbitTemplate.convertAndSend("topicA", "lazy.one", "hello topic B");
        rabbitTemplate.convertAndSend("topicA", "A.A.rabbit", "hello topic A");
    }

    @RabbitListener(queues = "#{topicQueueB.name}")
    @RabbitHandler
    public void topicQueueBHandler(String data) {
        System.out.println("topicQueueBHandler " + data);
    }

    @RabbitListener(queues = "topic query A")
    @RabbitHandler
    public void topicQueueAHandler(String data) {
        System.out.println("topicQueueAHandler " + data);
    }
}
