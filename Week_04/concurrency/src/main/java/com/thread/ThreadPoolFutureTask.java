package com.thread;

import java.util.concurrent.*;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-05 22:53
 **/
public class ThreadPoolFutureTask {
    private final static ExecutorService executor = Executors.newSingleThreadExecutor();
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            public Integer call() throws Exception {
                return Sum.get();
            }
        });
        try {
            executor.submit(task);
            System.out.println("异步计算结果为："+task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        executor.shutdown();
    }
}
