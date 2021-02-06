package com.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-06 20:31
 **/
public class ThreadCyclicBarrier {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger();
        CyclicBarrier cb = new CyclicBarrier(2);
        new Thread(new MyThread6(ai, cb)).start();
        try {
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为："+ai);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

}

class MyThread6 implements Runnable {
    private AtomicInteger ai;
    private CyclicBarrier cb;
    public MyThread6 (AtomicInteger ai, CyclicBarrier cb) {
        this.ai = ai;
        this.cb = cb;
    }
    public void run() {
        ai.set(Sum.get());
        try {
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
