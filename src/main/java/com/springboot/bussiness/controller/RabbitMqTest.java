package com.springboot.bussiness.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    private AmqpTemplate rabbitTemplate;

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

    //rebbitmq topic
}
