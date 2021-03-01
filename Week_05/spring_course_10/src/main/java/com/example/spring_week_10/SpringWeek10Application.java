package com.example.spring_week_10;

import com.example.starterdemo.bean.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWeek10Application implements CommandLineRunner {

    @Autowired
    private School school;

    public static void main(String[] args) {
        SpringApplication.run(SpringWeek10Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(school.toString());
        school.ding();
    }
}
