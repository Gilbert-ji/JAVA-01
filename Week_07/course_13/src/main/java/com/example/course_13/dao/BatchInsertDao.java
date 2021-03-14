package com.example.course_13.dao;

import com.example.course_13.util.JdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-07 22:47
 **/
public class BatchInsertDao {

    public static void batchInsert(Connection conn){
        System.out.println("数据插入开始-----");
        PreparedStatement ps = null;
        long begin = System.currentTimeMillis();
        try {
            String sql = "INSERT INTO t_order(order_no,user_id,payment,payment_type,postage,status,create_time) " +
                    "VALUES (?,?,?,?,?,?,now())";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 1000000; i++) {
                ps.setInt(1, new Random().nextInt(1000000));
                ps.setInt(2, new Random().nextInt(1000000));
                ps.setDouble(3, 10.00);
                ps.setInt(4, 1);
                ps.setInt(5, 5);
                ps.setInt(6, 1);
                ps.addBatch();
                if(i % 100000 == 99999){
                    ps.executeBatch();
                    conn.commit();
                }
            }
            ps.executeBatch();
            conn.commit();
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps, null);
        }
        long end = System.currentTimeMillis();
        System.out.println("插入100万条数据，耗时：" + (end-begin)/1000 + "s");
    }

    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/test_demo?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "biao123";
        Connection conn = null;
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            conn.setAutoCommit(false);
            batchInsert(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
