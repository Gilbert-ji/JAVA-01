package com.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @desc: ThreadLockSupport
 * @author: biao
 * @create: 2021-02-06 20:19
 **/
public class ThreadLockSupport {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger();
        new Thread(new MyThread4(ai, Thread.currentThread())).start();
        LockSupport.park();
        System.out.println("异步计算结果为："+ai);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

}

class MyThread4 implements Runnable {
    private AtomicInteger ai;
    private Thread t;
    public MyThread4(AtomicInteger ai, Thread t){
        this.ai = ai;
        this.t = t;
    }
    public void run() {
        ai.set(Sum.get());
        LockSupport.unpark(t);
    }
}
