package com.example.course_14.aop;

import org.springframework.stereotype.Component;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-13 11:54
 **/
@Component
//@Lazy(false)
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getDbType() {
        return contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }

}
