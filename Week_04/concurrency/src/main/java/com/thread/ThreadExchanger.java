package com.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-06 22:01
 **/
public class ThreadExchanger {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        Exchanger<Integer> ex = new Exchanger<>();
        new Thread(new MyThread10(ex)).start();
        Integer ai = 0;
        try {
            ai = ex.exchange(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为："+ai);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

class MyThread10 implements Runnable{
    private Exchanger<Integer> exchanger;
    public MyThread10 (Exchanger exchanger) {
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        try {
            exchanger.exchange(Sum.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
