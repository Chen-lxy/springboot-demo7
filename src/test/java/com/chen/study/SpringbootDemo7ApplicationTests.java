package com.chen.study;

import com.chen.study.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest
class SpringbootDemo7ApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void addUser()throws Exception{
        // 插入数据
        String sql = "insert into user(username,password) values('lisi','lisi')";
        int rows = jdbcTemplate.update(sql);
        System.out.println(rows);
    }

    @Test
    public void queryUser(){
        //  查询用户
        String name = "anna";
        String sql = "select * from user where username = ?";
        List<User> users = jdbcTemplate.query(sql, new User(), new Object[]{name});
        for (User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void queryAll() throws Exception{
        // 查询所有用户信息
        String sql = "select * from user limit 1000";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        for (User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void updateUser(){
        // 更新信息
        Integer id = 1;
        String pwd = "123";
        String sql = "update user set password = ? where id = ?";
        int update = jdbcTemplate.update(sql, pwd, id);
        System.out.println(update);
    }

    @Test
    public void deleteUser(){
        // 删除用户
        String sql = "delete from user where id = ?";
        int update = jdbcTemplate.update(sql, 1);
        System.out.println(update);
    }

    @Test
    void contextLoads() {
    }

}
