package com.example.course22.jedis;

import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-03 17:36
 **/
public class JedisDemo {


    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println(jedis.info());
        System.out.println("------------");
        jedis.set("jedis:kk", "jedis_string");

        // hash
        Map<String, String> map = new HashMap<>();
        map.put("name", "jack");
        map.put("desc", "a student");
        map.put("age", "13");
        System.out.println(jedis.hset("jedis:hash", map));

        // 列表（list）
        List<String> list = Arrays.asList("lily", "lucy", "jack");
        list.forEach(s -> jedis.lpush("jedis:list", s));

        // 集合（set）
        jedis.sadd("jedis:set", "hhhh");
        jedis.sadd("jedis:set", "yyyy");

        // 有序集合（sortset）
        jedis.zadd("jedis:sortset1", 34, "yyyy");
        jedis.zadd("jedis:sortset1", 1, "ttttt");
        jedis.zadd("jedis:sortset1", 6, "uuuuu");

        System.out.println(jedis.get("jedis:kk"));
        System.out.println(jedis.hgetAll("jedis:hash"));
        System.out.println(jedis.lrange("jedis:list", 0, -1));
        System.out.println(jedis.smembers("jedis:set"));
        System.out.println(jedis.zrange("jedis:sortset1", 0, -1));
        System.out.println(jedis.zrevrange("jedis:sortset1", 0, -1));
    }

}
