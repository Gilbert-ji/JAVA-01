package com.example.activemqdemo.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-10 22:49
 **/
@Configuration
public class MqConfig {

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("queue_demo");
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic("topic_demo");
    }

}
