package com.example.course_26.kmq.core;

import java.util.HashMap;

public class KmqConsumer<T> {

    private final KmqBroker broker;

    private Kmq kmq;

    public KmqConsumer(KmqBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public KmqMessage<T> poll(String consumer) {
        int offset = broker.getConsumerOffset().getOrDefault(consumer, 0);
        KmqMessage msg = kmq.poll(offset);
        if(null != msg){
            broker.getConsumerOffset().put(consumer, offset++);
            if(msg.getHeaders() == null) {
                msg.setHeaders(new HashMap<>(1));
            }
            msg.getHeaders().put("offset", offset);
        }
        return msg;
    }

    public void commitOffset(String consumer, Integer offset){
        broker.getConsumerOffset().put(consumer, offset);
    }

}
