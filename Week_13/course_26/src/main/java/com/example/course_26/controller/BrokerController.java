package com.example.course_26.controller;

import com.alibaba.fastjson.JSON;
import com.example.course_26.kmq.core.KmqBroker;
import com.example.course_26.kmq.core.KmqConsumer;
import com.example.course_26.kmq.core.KmqMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @desc:
 * @author: biao
 * @create: 2021-05-16 11:25
 **/
@RestController
@RequestMapping("/broker")
public class BrokerController {

    @Autowired
    private KmqBroker kmqBroker;

    @RequestMapping("/createTopic")
    public void createTopic(@RequestBody String name){
        kmqBroker.createTopic(name);
        KmqConsumer consumer = kmqBroker.createConsumer();
        consumer.subscribe(name);
    }

    @RequestMapping("/send")
    public void send(@RequestBody Map<String, Object> map){
        kmqBroker.createProducer().send((String) map.get("topic"),
                JSON.parseObject((String) map.get("message"), KmqMessage.class));
    }

    @RequestMapping("/poll")
    public KmqMessage poll(@RequestBody Map<String, String> map){
        KmqConsumer consumer = kmqBroker.createConsumer();
        consumer.subscribe(map.get("topic"));
        return consumer.poll(map.get("consumer"));
    }

    @RequestMapping("/commitOffset")
    public void commitOffset(@RequestBody Map<String, Object> map) {
        kmqBroker.createConsumer().commitOffset((String)map.get("consumer"), (Integer)map.get("offset"));
    }

}
