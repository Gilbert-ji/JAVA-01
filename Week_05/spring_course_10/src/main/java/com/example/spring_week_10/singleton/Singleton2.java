package com.example.spring_week_10.singleton;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-21 11:42
 **/
public class Singleton2 {

    private volatile static Singleton2 singleton2;

    private Singleton2 () {}

    public static Singleton2 getInstance(){
        if (null == singleton2) {
            synchronized (Singleton2.class) {
                if(null == singleton2){
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }

}
