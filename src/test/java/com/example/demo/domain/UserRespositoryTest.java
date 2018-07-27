package com.example.demo.domain;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRespositoryTest {
    @Autowired
    private UserRespository userRespository;

    @Test
    public void test() throws Exception{
        userRespository.deleteAll();
        userRespository.save(new User("Tom","001"));
        userRespository.save(new User("Poly","002"));
        System.out.println(userRespository.findAll());
        Assert.assertEquals(2,userRespository.findAll().size());
    }

    @Test
    //@Transactional
    public void testBaseQuery() throws Exception{
        //try {
            User user = new User("Davy", "123");
            System.out.println(userRespository.findAll());
            System.out.println(userRespository.count());
            userRespository.save(user);
            //throw new Exception("hello");
        //}catch (Exception e){
        //    TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
        //}
    }
}
