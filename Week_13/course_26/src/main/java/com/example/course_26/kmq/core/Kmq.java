package com.example.course_26.kmq.core;

import java.util.Arrays;

public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new KmqMessage[capacity];
    }

    private String topic;

    private int capacity;

    private int offset;

    private KmqMessage[] queue;

    public void send(KmqMessage message) {
        queue[offset] = message;
        offset++;
    }

    public KmqMessage poll(int offset) {
        return queue[offset];
    }

    @Override
    public String toString() {
        return "Kmq{" +
                "topic='" + topic + '\'' +
                ", capacity=" + capacity +
                ", offset=" + offset +
                ", queue=" + Arrays.toString(queue) +
                '}';
    }
}
