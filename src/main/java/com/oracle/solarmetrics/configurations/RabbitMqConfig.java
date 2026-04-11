package com.oracle.solarmetrics.configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable("email-welcome").build();
    }

    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder.directExchange("email-welcome.ex").build();
    }

    @Bean
    public Binding binding(Queue orderQueue, Exchange orderExchange) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderExchange)
                .with("email-welcome.rk")
                .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}