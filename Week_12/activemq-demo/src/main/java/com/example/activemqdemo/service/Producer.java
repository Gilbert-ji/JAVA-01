package com.example.activemqdemo.service;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-10 22:51
 **/
@Component
public class Producer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue queue;

    @Resource
    private Topic topic;

    public void sendMsg(String msg){
        System.out.println("发送队列消息： " + msg);
        jmsMessagingTemplate.convertAndSend(queue, msg);
    }

    public void sendTopic(String msg){
        System.out.println("发送topic消息：" + msg);
        jmsMessagingTemplate.convertAndSend(topic, msg);
    }

}
