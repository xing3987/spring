package com.example.demo.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/*
整合mybatis对数据库进行操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() throws Exception{
        System.out.println(userMapper.getAll());
        System.out.println(userMapper.getOne(2l));
    }

    @Test
    public void testUpdate() throws Exception{
        User user= userMapper.getOne(2l);
        System.out.println(user);
        user.setPassword("010");
        userMapper.update(user);
        System.out.println(userMapper.getOne(2l));
    }

    @Test
    public void testInsert() throws Exception{
        User user=new User("SuperMan","030");
        userMapper.insert(user);
        System.out.println(userMapper.getAll());
    }

    @Test
    public void testDelete() throws Exception{
        userMapper.delete(5l);
        System.out.println(userMapper.getAll());
    }
}
