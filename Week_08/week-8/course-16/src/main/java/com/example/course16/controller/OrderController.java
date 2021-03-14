package com.example.course16.controller;

import com.example.course16.service.OrderServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-14 09:28
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderServiceImpl orderService;

    @GetMapping("/check")
    public Object check(){
        return "Ok";
    }

    @GetMapping("/save")
    public Object insert(){

        return orderService.insert();
    }

    @GetMapping("/findByOrder")
    public Object findByOrder(int orderNo){
        return orderService.findByOrderNo(orderNo);
    }

}
