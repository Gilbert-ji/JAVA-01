package com.example.course_13.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-09 23:07
 **/
public class BatchInsertDao3 {

    static String url = "jdbc:mysql://localhost:3306/test_demo?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8";
    static String name = "com.mysql.jdbc.Driver";
    static String user = "root";
    static String password = "biao123";

    public static void insert(){
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setDriverClassName(name);
        dmds.setUrl(url);
        dmds.setUsername(user);
        dmds.setPassword(password);
        JdbcTemplate jt = new JdbcTemplate(dmds);
        String sql = "INSERT INTO t_order(order_no,user_id,payment,payment_type,postage,status,create_time) " +
                "VALUES (?,?,?,?,?,?,now())";
        jt.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, new Random().nextInt(100000000));
                ps.setInt(2, new Random().nextInt(200000000));
                ps.setDouble(3, 10.00);
                ps.setInt(4, 1);
                ps.setInt(5, 5);
                ps.setInt(6, 1);
            }

            @Override
            public int getBatchSize() {
                return 1000000;
            }
        });
    }


    public static void main(String[] args) {
        System.out.println("start----");
        long s = System.currentTimeMillis();
        insert();
        long e = System.currentTimeMillis();
        System.out.println("耗时--"+(e-s)/1000);
    }
}
