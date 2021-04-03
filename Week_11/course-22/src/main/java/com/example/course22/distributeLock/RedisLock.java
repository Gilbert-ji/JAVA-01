package com.example.course22.distributeLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-03 22:17
 **/
@Component
public class RedisLock {

    private String KEY_LOCK = "key_lock";

    @Autowired
    JedisPool jedisPool;

    public boolean lock(String key){
        Jedis jedisPoolResource = jedisPool.getResource();
        long s = System.currentTimeMillis();
        try {
            while (true) {
                String lock = jedisPoolResource.set(KEY_LOCK, key, SetParams.setParams().nx().px(5000));
                if("OK".equals(lock)){
                    return true;
                }
                long time = System.currentTimeMillis() - s;
                if(time > 50000){
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedisPoolResource.close();
        }
    }

    public boolean unlock(String key){
        Jedis jedis = jedisPool.getResource();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then" +
                "   return redis.call('del', KEYS[1]) " +
                "else" +
                "   return 0 " +
                "end";
        try {
            Object eval = jedis.eval(script, Collections.singletonList(KEY_LOCK), Collections.singletonList(key));
            if ("1".equals(eval.toString())){
                return true;
            }
            return false;
        } finally {
            jedis.close();
        }
    }

}

