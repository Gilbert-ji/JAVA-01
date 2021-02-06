package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-05 22:28
 **/
public class MyThread1 implements Runnable {
    private AtomicInteger ai;
    public MyThread1(AtomicInteger ai){
        this.ai = ai;
    }

    public void run() {
        ai.set(Sum.get());
    }
}