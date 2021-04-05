package com.example.mybatis_demo.service;

import com.example.mybatis_demo.bean.User;
import com.example.mybatis_demo.mapper.master.UserMasterMapper;
import com.example.mybatis_demo.mapper.slave.UserSlaveMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-04 18:32
 **/
@Service
public class UserService {

    @Resource
    private UserMasterMapper userMasterMapper;
    @Resource
    private UserSlaveMapper userSlaveMapper;

    @Transactional
    public User update(User user){
        userMasterMapper.update(user);
        return userMasterMapper.findById(user.getId());
    }

    public User masterFindById(Integer id){
        return userMasterMapper.findById(id);
    }

    public User findById(Integer id){
        return userSlaveMapper.findById(id);
    }

}
