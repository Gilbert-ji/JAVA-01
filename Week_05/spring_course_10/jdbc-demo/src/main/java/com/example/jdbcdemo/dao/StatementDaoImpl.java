package com.example.jdbcdemo.dao;

import com.example.jdbcdemo.bean.User;
import com.example.jdbcdemo.util.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @desc: statement
 * @author: biao
 * @create: 2021-03-01 21:52
 **/
@Repository
public class StatementDaoImpl implements UserDao {

    @Override
    public List<User> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            String sql = "select * from user";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, st, rs);
        }
        return list;
    }

    @Override
    public User findById(Integer id) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            String sql = "select * from user where id = " + id;
            rs = st.executeQuery(sql);
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, st, rs);
        }
        return user;
    }

    @Override
    public void save(User user) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            String sql = "insert into user(name,age) values('"+user.getName()+"', "+user.getAge()+")";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, st, null);
        }
    }

    @Override
    public void update(User user) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            String sql = "update user set name = '"+user.getName()+"', age = "+user.getAge()+" where id = " + user.getId();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, st, null);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            String sql = "delete from user where id = " + id;
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, st, null);
        }
    }
}
