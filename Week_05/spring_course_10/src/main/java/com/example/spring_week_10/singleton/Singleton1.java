package com.example.spring_week_10.singleton;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-21 11:41
 **/
public class Singleton1 {
    private static Singleton1 singleton1 = new Singleton1();
    private Singleton1(){}
    public static Singleton1 getInstance() {
        return singleton1;
    }
}
