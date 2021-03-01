package com.example.spring_week_09.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-19 22:47
 **/
@Data
@Component
@ToString
public class Student {
    private long id = 0;
    private String name;
    private int age;
    private double score;
    public void goSchool(){
        System.out.println("------goSchool---");
    }
}
