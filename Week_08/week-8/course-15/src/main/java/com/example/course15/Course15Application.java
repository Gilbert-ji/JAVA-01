package com.example.course15;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.course15.dao")
public class Course15Application {

    public static void main(String[] args) {
        SpringApplication.run(Course15Application.class, args);
    }

}
