package com.example.course_13;

import java.util.Random;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-07 22:48
 **/
public class DemoTest {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(1000000));
            System.out.println(random.nextDouble());
        }
    }

}
