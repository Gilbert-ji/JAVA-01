package com.example.course_14.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-13 11:13
 **/
@Aspect
@Component
@Order(0)//Order设定AOP执行顺序 使之在数据库事务上先执行
public class SwitchDataSource {

    @Pointcut("@annotation(MyDataSource)")
    public void myPointCut(){}

//    @Before("execution(* com.example.course_14.dao.*.*(..))")
//    public void process(JoinPoint joinPoint){
//        String methodName = joinPoint.getSignature().getName();
//        if (methodName.startsWith("get")
//                ||methodName.startsWith("count")
//                ||methodName.startsWith("find")
//                ||methodName.startsWith("list")
//                ||methodName.startsWith("select")
//                ||methodName.startsWith("check")){
//            DataSourceContextHolder.setDbType("slaveDataSource");
//        } else {
//            DataSourceContextHolder.setDbType("masterDataSource");
//        }
//    }

    @Before("myPointCut()")
    public void beforeMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        Class<?> aClass = joinPoint.getTarget().getClass();
        String name = joinPoint.getSignature().getName();
        Method method = aClass.getMethod(name);
        String value = method.getAnnotation(MyDataSource.class).value();
        if("master".equals(value)){
            DataSourceContextHolder.setDbType("masterDataSource");
        } else {
            DataSourceContextHolder.setDbType("slaveDataSource");
        }
    }

}
