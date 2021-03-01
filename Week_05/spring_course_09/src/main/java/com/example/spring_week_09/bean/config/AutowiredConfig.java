package com.example.spring_week_09.bean.config;

import com.example.spring_week_09.bean.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @desc: 自动化装配
 * @author: biao
 * @create: 2021-02-19 22:58
 **/
@Configuration
@ComponentScan
public class AutowiredConfig {

    @Autowired
    private School school1;

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutowiredConfig.class);
        School school = (School) ac.getBean("school1");
        school.welcome();
    }
}
