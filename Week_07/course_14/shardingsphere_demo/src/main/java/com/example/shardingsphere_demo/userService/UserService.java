package com.example.shardingsphere_demo.userService;

import com.example.shardingsphere_demo.bean.User;
import com.example.shardingsphere_demo.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-05 11:11
 **/
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public List<User> getAll(){
        return userDao.getAll();
    }

    @Transactional
    public User insert(User user){
        userDao.insert(user);
        return userDao.findById(user.getId());
    }

}
