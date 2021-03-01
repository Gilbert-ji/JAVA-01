package com.example.jdbcdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc: user
 * @author: biao
 * @create: 2021-02-28 23:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private int id;
    private String name;
    private int age;

}
