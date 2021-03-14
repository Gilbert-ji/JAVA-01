package com.example.course15.dao;

import com.example.course15.bean.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-14 09:24
 **/
public interface OrderDao {

    int insert(@Param("bean") Order order);
    int update(@Param("bean") Order order);
    Order findByOrderNo(int orderNo);
    List<Order> findAll();

}
