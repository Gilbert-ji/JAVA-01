package com.example.spring_week_09.aop;

import com.example.spring_week_09.aop.cglib.SayCGlibProxy;
import com.example.spring_week_09.aop.proxy.SayHello;
import com.example.spring_week_09.aop.proxy.SayProxy;
import com.example.spring_week_09.aop.proxy.SaySomething;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-19 22:13
 **/
public class Main {

    public static void main(String[] args) {
        // 基于JDK的代理
        SaySomething saySomething = new SayHello();
        InvocationHandler handler = new SayProxy(saySomething);
        SaySomething proxySay = (SaySomething) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                saySomething.getClass().getInterfaces(), handler);
        proxySay.say("jdk proxy");

        //基于CGLib的代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SayHello.class);
        enhancer.setCallback(new SayCGlibProxy());
        SaySomething cglibSay = (SaySomething) enhancer.create();
        cglibSay.say("CGLib proxy");
    }

}
