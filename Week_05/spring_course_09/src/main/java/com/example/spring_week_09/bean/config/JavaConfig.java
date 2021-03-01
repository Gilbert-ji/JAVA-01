package com.example.spring_week_09.bean.config;

import com.example.spring_week_09.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc: 通过java bean注解装配
 * @author: biao
 * @create: 2021-02-21 11:19
 **/
@Configuration
public class JavaConfig {

    @Bean
    public Student getStudent(){
        Student s = new Student();
        s.setId(4);
        s.setName("lily");
        s.setAge(18);
        return s;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        Student student = (Student) ac.getBean("getStudent");
        System.out.println(student);
    }

}
