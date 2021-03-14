package com.example.course16.service;

import com.example.course16.bean.Order;
import com.example.course16.dao.OrderDao;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-14 15:29
 **/
@Service
public class OrderServiceImpl {
    @Resource
    private OrderDao orderDao;

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public Order insert(){
        Order order = new Order();
        Random random = new Random();
        order.setOrderNo(random.nextInt(10000000));
        order.setUserId(20000000 + random.nextInt(20));
        order.setPayment(30.5);
        order.setPaymentType(1);
        order.setPostage(5);
        order.setStatus(1);
        System.out.println("orderNo-" + order.getOrderNo() + ":: userId--" + order.getUserId());
        orderDao.insert(order);
        return orderDao.findByOrderNo(order.getOrderNo());
    }

    public Order findByOrderNo(int orderNo){
        return orderDao.findByOrderNo(orderNo);
    }
}
