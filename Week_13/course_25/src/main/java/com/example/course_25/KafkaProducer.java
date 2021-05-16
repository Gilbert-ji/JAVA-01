package com.example.course_25;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc: producer
 * @author: biao
 * @create: 2021-05-12 22:18
 **/
@RestController
@RequestMapping("/kafka")
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/sendMsg")
    public String sendMsg(String topic, String msg){
        kafkaTemplate.send(topic, msg);
        return "SUCCESS";
    }

}
