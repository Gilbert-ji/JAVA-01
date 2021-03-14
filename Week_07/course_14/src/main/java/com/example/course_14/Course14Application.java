package com.example.course_14;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
        (exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.example.course_14.dao")
public class Course14Application {

    public static void main(String[] args) {
        SpringApplication.run(Course14Application.class, args);
    }

}
