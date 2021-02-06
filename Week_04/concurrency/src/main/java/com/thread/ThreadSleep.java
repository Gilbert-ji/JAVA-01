package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-05 22:29
 **/
public class ThreadSleep {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger();
        new Thread(new MyThread1(ai)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为："+ai);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}
