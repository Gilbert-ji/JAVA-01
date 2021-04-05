package com.example.mybatis_demo.mapper.master;

import com.example.mybatis_demo.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * @desc:
 * @author: biao
 * @create: 2021-04-04 18:23
 **/
public interface UserMasterMapper {

    User findById(@Param("id")Integer id);

    int update(@Param("bean")User user);

}
