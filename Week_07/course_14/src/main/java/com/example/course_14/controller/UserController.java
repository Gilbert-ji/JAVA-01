package com.example.course_14.controller;

import com.example.course_14.aop.MyDataSource;
import com.example.course_14.bean.User;
import com.example.course_14.dao.UserDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-13 16:10
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserDao userDao;

    @GetMapping("/")
    public Object start(){
        return "OK";
    }

    @GetMapping("/getAll")
    @MyDataSource(value = "slave1")
    public Object getAll(){
        return userDao.getAll();
    }

    @GetMapping("/save")
    @MyDataSource
    public Object insert(){
        int num = new Random().nextInt(100);
        return userDao.insert(new User(num, "name" + num, "master"));
    }

}
