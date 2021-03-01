package com.example.spring_week_09.bean.config;

import com.example.spring_week_09.bean.Klass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-21 11:26
 **/
public class XmlConfig {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Klass class_1 = (Klass) ac.getBean("class_1");
        System.out.println(class_1.toString());
    }

}
