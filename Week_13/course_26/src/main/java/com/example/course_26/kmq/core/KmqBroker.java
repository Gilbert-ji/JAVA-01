package com.example.course_26.kmq.core;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class KmqBroker { // Broker+Connection

    public static final int CAPACITY = 10000;

    private final Map<String, Kmq> kmqMap = new ConcurrentHashMap<>(64);

    private Map<String, Integer> consumerOffset = new ConcurrentHashMap<>();

    public void createTopic(String name){
        kmqMap.putIfAbsent(name, new Kmq(name,CAPACITY));
        System.out.println("createTopic--" + kmqMap);
    }

    public Kmq findKmq(String topic) {
        return this.kmqMap.get(topic);
    }

    public KmqProducer createProducer() {
        return new KmqProducer(this);
    }

    public KmqConsumer createConsumer() {
        return new KmqConsumer(this);
    }

    public Map<String, Integer> getConsumerOffset() {
        return consumerOffset;
    }
}
