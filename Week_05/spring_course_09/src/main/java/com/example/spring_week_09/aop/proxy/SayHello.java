package com.example.spring_week_09.aop.proxy;

/**
 * @desc: say
 * @author: biao
 * @create: 2021-02-19 22:17
 **/
public class SayHello implements SaySomething {
    @Override
    public void say(String arg) {
        System.out.println("Hello " + arg);
    }
}
