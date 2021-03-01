package com.example.jdbcdemo.dao;

import com.example.jdbcdemo.bean.User;

import java.util.List;

/**
 * @desc: userDao
 * @author: biao
 * @create: 2021-02-28 23:21
 **/
public interface UserDao {

    List<User> findAll();
    User findById(Integer id);
    void save(User user);
    void update(User user);
    void deleteById(Integer id);

}
