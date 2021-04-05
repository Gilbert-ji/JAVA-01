package com.example.shardingsphere_demo;

import com.example.shardingsphere_demo.bean.User;
import com.example.shardingsphere_demo.userService.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-05 11:09
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void inset(){
        User user = userService.insert(new User(8, "hahah", "master"));
        log.info("新增用户：{}", user);
    }

    @Test
    public void find(){
        List<User> list1 = userService.getAll();
        List<User> list2 = userService.getAll();
        log.info("查询用户1： {}", list1);
        log.info("查询用户2： {}", list2);
    }

}
