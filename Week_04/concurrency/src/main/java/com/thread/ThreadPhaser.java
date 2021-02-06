package com.thread;

import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-06 21:45
 **/
public class ThreadPhaser {

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger();
        Phaser phaser = new Phaser(2);
        new Thread(new MyThread9(ai, phaser)).start();
        phaser.arriveAndAwaitAdvance();
        System.out.println("异步计算结果为："+ai);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

}

class MyThread9 implements Runnable{
    private AtomicInteger ai;
    private Phaser phaser;
    public MyThread9 (AtomicInteger ai, Phaser phaser){
        this.ai = ai;
        this.phaser = phaser;
    }
    public void run() {
        ai.set(Sum.get());
        phaser.arriveAndAwaitAdvance();
    }
}
