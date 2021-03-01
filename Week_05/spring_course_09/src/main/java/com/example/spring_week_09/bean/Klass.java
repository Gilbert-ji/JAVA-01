package com.example.spring_week_09.bean;

import lombok.Data;

import java.util.List;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-19 22:49
 **/
@Data
public class Klass {
    private String classCode;
    private List<Student> students;
}
