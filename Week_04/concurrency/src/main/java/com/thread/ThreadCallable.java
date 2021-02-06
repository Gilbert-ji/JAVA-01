package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-05 22:36
 **/
public class ThreadCallable {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
//        AtomicInteger ai = new AtomicInteger();
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return Sum.get();
            }
        };
        FutureTask<Integer> task = new FutureTask<Integer>(callable);
        new Thread(task).start();
        try {
            System.out.println("异步计算结果为："+task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

    }
}
