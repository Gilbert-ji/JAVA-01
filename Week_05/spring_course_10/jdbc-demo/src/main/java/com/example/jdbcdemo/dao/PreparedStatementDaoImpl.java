package com.example.jdbcdemo.dao;

import com.example.jdbcdemo.bean.User;
import com.example.jdbcdemo.util.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-01 22:38
 **/
@Repository
public class PreparedStatementDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps, rs);
        }
        return list;
    }

    @Override
    public User findById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from user where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps, rs);
        }
        return user;
    }

    @Override
    public void save(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "insert into user(name,age) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());

            ps.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps, null);
        }
    }

    @Override
    public void update(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "update user set name = ?, age = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setInt(3, user.getId());

            ps.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps, null);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "delete from user where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps, null);
        }
    }
}
