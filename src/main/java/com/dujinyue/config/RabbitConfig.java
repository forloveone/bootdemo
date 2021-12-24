package com.dujinyue.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    // topic b

    /**
     * (*) 可以代替一个单词.
     * (#) 可以替代零个或多个单词.
     */
    @Bean
    public TopicExchange topic() {
        return new TopicExchange("topicA");
    }

    @Bean
    public Queue topicQueueA() {
        return new Queue("topic query A");
    }

    @Bean
    public Queue topicQueueB() {
        return new Queue("topic query B");
    }

    @Bean
    public Binding binding1a(TopicExchange topic, Queue topicQueueA) {
        return BindingBuilder.bind(topicQueueA).to(topic).with("*.orange.*");
    }

    @Bean
    public Binding binding1b(TopicExchange topic, Queue topicQueueA) {
        return BindingBuilder.bind(topicQueueA).to(topic).with("*.*.rabbit");
    }

    @Bean
    public Binding binding2a(TopicExchange topic, Queue topicQueueB) {
        return BindingBuilder.bind(topicQueueB).to(topic).with("lazy.#");
    }
    // topic end
}
