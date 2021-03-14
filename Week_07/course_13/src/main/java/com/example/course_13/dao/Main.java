package com.example.course_13.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-09 22:32
 **/
@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        BatchInsertDao3 dao = (BatchInsertDao3) applicationContext.getBean("batchInsertDao");
        dao.insert();
        System.out.println("耗时--" + (System.currentTimeMillis() - s)/1000);
    }

}
