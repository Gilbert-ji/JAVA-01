package com.example.course22.redisTemp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-03 23:32
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTempDemo {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        redisTemplate.boundValueOps("pppp").set("79332");
        System.out.println(redisTemplate.boundValueOps("pppp").get());
        redisTemplate.boundHashOps("mmmm").put("name", "lily");
        System.out.println(redisTemplate.boundHashOps("mmmm").values());
    }

}
