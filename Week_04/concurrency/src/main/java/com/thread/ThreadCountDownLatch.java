package com.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc: ThreadCountDownLatch
 * @author: biao
 * @create: 2021-02-06 20:27
 **/
public class ThreadCountDownLatch {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger();
        CountDownLatch cdl = new CountDownLatch(1);
        new Thread(new MyThread5(ai, cdl)).start();
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为："+ai);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

}

class MyThread5 implements Runnable {
    private AtomicInteger ai;
    private CountDownLatch cdl;
    public MyThread5 (AtomicInteger ai, CountDownLatch cdl) {
        this.ai = ai;
        this.cdl = cdl;
    }
    public void run() {
        ai.set(Sum.get());
        cdl.countDown();
    }
}