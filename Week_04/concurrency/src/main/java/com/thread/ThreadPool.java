package com.thread;

import java.util.concurrent.*;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-05 22:47
 **/
public class ThreadPool {
    private final static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return Sum.get();
            }
        });
        try {
            System.out.println("异步计算结果为："+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        executor.shutdown();
    }
}
