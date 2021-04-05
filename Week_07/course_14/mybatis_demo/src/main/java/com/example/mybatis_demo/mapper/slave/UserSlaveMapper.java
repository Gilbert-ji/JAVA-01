package com.example.mybatis_demo.mapper.slave;

import com.example.mybatis_demo.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-04 18:24
 **/
public interface UserSlaveMapper {

    User findById(@Param("id")Integer id);

    int update(@Param("bean")User user);

}
