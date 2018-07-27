package com.example.demo.domain;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRespositoryTest {
    @Autowired
    private UserRespository userRespository;

    @Test
    public void test() throws Exception{
        userRespository.deleteAll();
        userRespository.save(new User(1,"Tom","001"));
        userRespository.save(new User(2,"Poly","002"));
        System.out.println(userRespository.findAll());
        Assert.assertEquals(2,userRespository.findAll().size());
    }
}
