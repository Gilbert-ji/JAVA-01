package com.example.activemqdemo.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-10 23:02
 **/
@Component
public class Consumer {

    @JmsListener(destination = "queue_demo")
    public void receiveMsg(String text){
        System.out.println("接收队列信息：" + text);
    }

    @JmsListener(destination = "topic_demo")
    public void receiveTopic1(String msg){
        System.out.println("receiveTopic1接收topic消息："+ msg);
    }

    @JmsListener(destination = "topic_demo")
    public void receiveTopic2(String msg){
        System.out.println("receiveTopic2接收topic消息："+ msg);
    }

}
