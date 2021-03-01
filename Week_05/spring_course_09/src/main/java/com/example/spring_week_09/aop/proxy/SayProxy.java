package com.example.spring_week_09.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-19 22:18
 **/
public class SayProxy implements InvocationHandler {

    private SaySomething saySomething;

    public SayProxy(SaySomething saySomething) {
        this.saySomething = saySomething;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-------say before--------");
        // 反射调用类中的方法
        Object invoke = method.invoke(saySomething, args);
        System.out.println("-------say end-----------");
        return invoke;
    }
}
