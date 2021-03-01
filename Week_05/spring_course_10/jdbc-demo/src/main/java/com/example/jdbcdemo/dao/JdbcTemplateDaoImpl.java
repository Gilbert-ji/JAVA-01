package com.example.jdbcdemo.dao;

import com.example.jdbcdemo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-28 23:22
 **/
@Repository
public class JdbcTemplateDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from user where id = ?", new BeanPropertyRowMapper<>(User.class), new Object[]{id});
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO `user`(`name`,age) VALUES(?,?)",user.getName(),user.getAge());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE `user` SET `name`=?,age=? WHERE id=?",user.getName(),user.getAge(),user.getId());
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM `user` WHERE id=?",id);
    }
}
