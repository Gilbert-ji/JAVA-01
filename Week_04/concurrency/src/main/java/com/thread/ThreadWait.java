package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-05 22:31
 **/
public class ThreadWait {

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger();
        Thread t = new Thread(new MyThread2(ai));
        t.start();
        synchronized (t) {
            try {
                t.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("异步计算结果为："+ai);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

}

class MyThread2 implements Runnable {
    private AtomicInteger ai;
    public MyThread2 (AtomicInteger ai){
        this.ai = ai;
    }
    public void run() {
        ai.set(Sum.get());
        synchronized (this) {
            notifyAll();
        }
    }
}
