package com.example.course_13.util;

import java.sql.*;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-01 21:56
 **/
public class JdbcUtil {

    private JdbcUtil(){}

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/test_demo?useUnicode=true&characterEncoding=UTF-8";
    private static String USER = "root";
    private static String PWD = "biao123";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
    }

    public static void release(Connection conn, Statement st, ResultSet rs){
        if(null != rs){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != st){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != conn){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
