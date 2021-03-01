package com.example.spring_week_09.aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-19 22:37
 **/
public class SayCGlibProxy implements MethodInterceptor {
    /**
     *
     * @param o 被代理的对象实例
     * @param method
     * @param objects
     * @param methodProxy   代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("------say begin-----");
        Object ob = methodProxy.invokeSuper(o, objects);
        System.out.println("------say end-----");
        return ob;
    }
}
