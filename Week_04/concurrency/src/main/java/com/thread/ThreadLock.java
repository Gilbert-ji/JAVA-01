package com.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-05 23:03
 **/
public class ThreadLock {

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger();
        Lock lock = new ReentrantLock();
        lock.lock();
        Condition condition = lock.newCondition();
        new Thread(new MyThread3(ai, lock, condition)).start();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为："+ai);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

}

class MyThread3 implements Runnable{
    private AtomicInteger ai;
    private Lock lock;
    private Condition condition;
    public MyThread3 (AtomicInteger ai, Lock lock, Condition condition) {
        this.ai = ai;
        this.lock = lock;
        this.condition = condition;
    }
    public void run() {
        lock.lock();
        ai.set(Sum.get());
        condition.signalAll();
        lock.unlock();
    }
}
