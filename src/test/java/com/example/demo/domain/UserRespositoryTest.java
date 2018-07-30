package com.example.demo.domain;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
/*
整合jpa，对数据库进行操作
 */
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

    @Test
    public void testPageQuery() throws Exception{
        int page=1,size=10;
        Sort sort=new Sort(Sort.Direction.DESC,"id");//定义排序规则
        Pageable pageable=new PageRequest(page,size,sort);//Pageable是spring封装的分页实现类
        System.out.println(userRespository.findAll(pageable));
        System.out.println(userRespository.findByUserName("Davy"));
        System.out.println(userRespository.findByUserName("Davy",pageable));
        System.out.println(userRespository.findByIdRange(1l,5l));
    }
}
