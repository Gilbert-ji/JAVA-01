package com.example.course_13;

import com.example.course_13.util.JdbcUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @desc: 100W条数据插入
 * @author: biao
 * @create: 2021-04-04 09:23
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchInsertTest {

    @Autowired
    private HikariDataSource dataSource;

    Timestamp timestamp;
    Connection conn;

    @Before
    public void before(){
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = LocalDateTime.now().atZone(zoneId).toInstant();
        timestamp = new Timestamp(instant.toEpochMilli());

        final String url = "jdbc:mysql://localhost:3306/test_demo?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "biao123";
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程批量插入
     */
    @Test
    public void insert1(){
        // 线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        // 计时
        long start = System.currentTimeMillis();
        // 循环创建线程，然后批量插入数据
        for (int i = 0; i < 5; i++) {
            try {
                Connection connection = dataSource.getConnection();
                String sql = "INSERT INTO t_order(order_no,user_id,payment,payment_type,postage,status,payment_time,create_time) " +
                        "VALUES (?,?,?,?,?,?,?,now())";
                AtomicReference<PreparedStatement> statement = new AtomicReference<>(connection.prepareStatement(sql));
                threadPool.execute(() -> {
                    PreparedStatement ps = statement.get();
                    for (int j = 0; j < 200000; j++) {
                        try {
                            ps.setInt(1, new Random().nextInt(1000000));
                            ps.setInt(2, new Random().nextInt(1000000));
                            ps.setDouble(3, 10.00);
                            ps.setInt(4, 1);
                            ps.setInt(5, 5);
                            ps.setInt(6, 1);
                            ps.setTimestamp(7, timestamp);
                            ps.addBatch();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        ps.executeBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            connection.close();
                            countDownLatch.countDown();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("多线程批量插入，耗时：" + (System.currentTimeMillis() - start)/1000);
        threadPool.shutdown();
    }

    /**
     * PreparedStatement批量插入功能，没10W插入一次，相当于串行插入
     */
    @Test
    public void insert2(){
        PreparedStatement ps = null;
        long begin = System.currentTimeMillis();
        try {
            String sql = "INSERT INTO t_order(order_no,user_id,payment,payment_type,postage,status,payment_time,create_time) " +
                    "VALUES (?,?,?,?,?,?,?,now())";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 1000000; i++) {
                ps.setInt(1, new Random().nextInt(1000000));
                ps.setInt(2, new Random().nextInt(1000000));
                ps.setDouble(3, 10.00);
                ps.setInt(4, 1);
                ps.setInt(5, 5);
                ps.setInt(6, 1);
                ps.setTimestamp(7, timestamp);
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

}
