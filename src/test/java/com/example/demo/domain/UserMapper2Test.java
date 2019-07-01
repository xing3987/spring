/*
<!--使用配置文件的方式使用整合mabatis时使用-->
 */
package com.example.demo.domain;

import com.example.demo.mapper.UserMapper2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapper2Test {

    @Autowired
    UserMapper2 userMapper2;

    @Test
    public void testSelect() throws Exception{
        System.out.println(userMapper2.getAll());
        System.out.println(userMapper2.getOne(2l));
    }
}
