package com.example.activemqdemo;

import com.example.activemqdemo.service.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-10 23:04
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMqTest {

    @Autowired
    private Producer producer;

    @Test
    public void queueTest(){
        producer.sendMsg("this is a queue!");
    }

    @Test
    public void topicTest(){
        producer.sendTopic("this is a topic!");
    }

}
