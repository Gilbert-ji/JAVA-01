package com.example.course15.bean;

import lombok.Data;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-14 09:18
 **/
@Data
public class Order {

    private int id;
    private int orderNo;
    private int userId;
    private double payment;
    private int paymentType;
    private int postage;
    private int status;
    private String paymentTime;
    private String sendTime;
    private String endTime;
    private String closeTime;
    private String createTime;
    private String updateTime;

}
