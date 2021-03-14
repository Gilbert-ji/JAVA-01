package com.example.shardingsphere_demo.dao;

import com.example.shardingsphere_demo.bean.User;

import java.util.List;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-13 16:04
 **/
public interface UserDao {
    List<User> getAll();

    int insert(User user);
}
