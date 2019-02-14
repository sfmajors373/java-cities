package com.lambdaschool.cities;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CitiesApplication
{

    public static final String EXCHANGE_NAME = "LambdaServer";
    public static final String QUEUE_NAME_SECRET = "SecretQueue";
    public static final String QUEUE_NAME_CITY1 = "City1Queue";
    public static final String QUEUE_NAME_CITY2 = "City2Queue";

    public static void main(String[] args)
    {
        SpringApplication.run(CitiesApplication.class, args);
    }

    @Bean
    public TopicExchange appExchange()
    {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueueSecret()
    {
        return new Queue(QUEUE_NAME_SECRET);
    }

    @Bean
    public Queue appQueueCity1()
    {
        return new Queue(QUEUE_NAME_CITY1);
    }

    @Bean
    public Queue appQueueCity2()
    {
        return new Queue(QUEUE_NAME_CITY2);
    }

    @Bean
    public Binding declareBindingSecret()
    {
        return BindingBuilder.bind(appQueueSecret()).to(appExchange()).with(QUEUE_NAME_SECRET);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConvertor()
    {
        return new Jackson2JsonMessageConverter();
    }

}

