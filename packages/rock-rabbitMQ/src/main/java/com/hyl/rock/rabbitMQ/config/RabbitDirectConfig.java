package com.hyl.rock.rabbitMQ.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDirectConfig {

    // 1. 定义直连交换机（名称：direct_exchange，持久化：true，自动删除：false）
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct_exchange", true, false);
    }

    // 2. 定义队列（名称：direct_queue，持久化：true，排他：false，自动删除：false）
    @Bean
    public Queue directQueue() {
        return new Queue("direct_queue", true, false, false);
    }

    // 3. 绑定交换机与队列（指定 Routing Key：direct_routing_key）
    @Bean
    public Binding directBinding(DirectExchange directExchange, Queue directQueue) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("direct_routing_key");
    }

}
