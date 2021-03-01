package com.example.jdbcdemo.controller;

import com.example.jdbcdemo.bean.User;
import com.example.jdbcdemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-28 23:29
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("hikariUserDaoImpl")
    private UserDao userDao;

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userDao.findAll();
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable Integer id){
        return userDao.findById(id);
    }

    @GetMapping("/save")
    public void save(User user){
        userDao.save(user);
    }

    @GetMapping("/update")
    public void update(User user){
        userDao.update(user);
    }

    @GetMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Integer id){
        userDao.deleteById(id);
    }
}
