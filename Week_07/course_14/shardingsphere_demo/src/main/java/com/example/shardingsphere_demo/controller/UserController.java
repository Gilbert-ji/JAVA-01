package com.example.shardingsphere_demo.controller;

import com.example.shardingsphere_demo.bean.User;
import com.example.shardingsphere_demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-13 16:10
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public Object start(){
        return "OK";
    }

    @GetMapping("/getAll")
    public Object getAll(){
        return userDao.getAll();
    }

    @GetMapping("/save")
    public Object insert(){
        int num = new Random().nextInt(100);
        return userDao.insert(new User(num, "name" + num, "master"));
    }

}
