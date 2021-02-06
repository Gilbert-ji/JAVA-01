package com.thread;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-05 22:22
 **/
public class Sum {

    private Sum(){

    }

    public static int get() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
