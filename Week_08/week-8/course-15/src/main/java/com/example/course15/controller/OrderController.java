package com.example.course15.controller;

import com.example.course15.bean.Order;
import com.example.course15.dao.OrderDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-14 09:28
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderDao orderDao;

    @GetMapping("/check")
    public Object check(){
        return "Ok";
    }

    @GetMapping("/save")
    public Object insert(){
        Order order = new Order();
        Random random = new Random();
        order.setOrderNo(random.nextInt(10000000));
        order.setUserId(20000000 + random.nextInt(20));
        order.setPayment(30.5);
        order.setPaymentType(1);
        order.setPostage(5);
        order.setStatus(1);
        System.out.println("orderNo-" + order.getOrderNo() + ":: userId--" + order.getUserId());
        return orderDao.insert(order);
    }

    @GetMapping("/findByOrder")
    public Object findByOrder(int orderNo){
        return orderDao.findByOrderNo(orderNo);
    }

}
