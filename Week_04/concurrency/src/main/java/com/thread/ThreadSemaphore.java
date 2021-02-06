package com.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc: ThreadSemaphore
 * @author: biao
 * @create: 2021-02-06 20:43
 **/
public class ThreadSemaphore {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger();
        Semaphore semaphore = new Semaphore(1);
        new Thread(new MyThread7(ai, semaphore)).start();
        try {
            semaphore.acquire(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(2);
        }
        System.out.println("异步计算结果为："+ai);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

class MyThread7 implements Runnable {
    private AtomicInteger ai;
    private Semaphore semaphore;
    public MyThread7 (AtomicInteger ai, Semaphore semaphore) {
        this.ai = ai;
        this.semaphore = semaphore;
    }
    public void run() {
        ai.set(Sum.get());
        semaphore.release(2);
        try {
            semaphore.acquire(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(2);
        }
    }
}
