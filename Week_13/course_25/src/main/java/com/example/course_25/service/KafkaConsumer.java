package com.example.course_25.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @desc: consumer
 * @author: biao
 * @create: 2021-05-12 22:21
 **/
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = {"kafka-test"})
    public void listen(ConsumerRecord record){
        log.info("主题-{}，消息-{}", record.topic(), record.value());
    }

}
