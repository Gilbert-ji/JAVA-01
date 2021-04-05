package com.example.mybatis_demo;

import com.example.mybatis_demo.bean.User;
import com.example.mybatis_demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-04 18:35
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void updateTest(){
        User user = userService.update(new User(2, "tony", "master"));
        log.info("更新后的结果：{0}" , user);
        log.info("获取会员：{0}" , userService.masterFindById(user.getId()));
    }

    @Test
    public void findByIdTest(){
        log.info("获取用户：" , userService.findById(1));
    }

}
