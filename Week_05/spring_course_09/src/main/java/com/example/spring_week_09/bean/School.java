package com.example.spring_week_09.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-19 22:50
 **/
@Data
@Component("school1")
public class School {
    private long schoolCode;
    private String schoolName;
    private List<Klass> klassList;
    public void welcome(){
        System.out.println("welcome student!!");
    }
}
