package com.example.course22.controller;

import com.example.course22.distributeLock.RedisLock;
import com.example.course22.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-03 22:34
 **/
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    RedisLock redisLock;

    int count = 100000;

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        ExecutorService executor = Executors.newFixedThreadPool(1000);
        long start = System.currentTimeMillis();
        IdWorker idWorker = new IdWorker(1,1,1);

        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                String id = String.valueOf(idWorker.nextId());

                try {
                    redisLock.lock(id);
                    count--;
                } finally {
                    redisLock.unlock(id);
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end  = System.currentTimeMillis();
        System.out.println("执行耗时： " + (end-start) + ", count值为 " + count);
        return "OK";
    }

}
