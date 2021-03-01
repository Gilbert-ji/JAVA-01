package com.example.spring_week_10.singleton;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-21 11:47
 **/
public class Singleton3 {

    private static class InnerSingleton{
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    private Singleton3(){}

    public static Singleton3 getInstance(){
        return InnerSingleton.INSTANCE;
    }

}
