package com.example.demo.domain;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/*
整合jpa，对数据库进行操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRespositoryTest {
    @Autowired
    private UserRespository userRespository;

    @Test
    public void test() throws Exception {
        userRespository.deleteAll();
        userRespository.save(new User("Tom", "001"));
        userRespository.save(new User("Poly", "002"));
        System.out.println(userRespository.findAll());
        Assert.assertEquals(2, userRespository.findAll().size());
    }

    @Test
    //@Transactional
    public void testBaseQuery() throws Exception {
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
    public void testPageQuery() throws Exception {
        int page = 0, size = 2;
        Sort sort = new Sort(Sort.Direction.DESC, "id");//定义排序规则
        Pageable pageable = new PageRequest(page, size, sort);//Pageable是spring封装的分页实现类
        Page<User> all = userRespository.findAll(pageable);
        List<User> list = all.getContent();
        System.out.println(list);

        System.out.println(userRespository.findByUserName("Davy"));

        Page<User> all2 = userRespository.findByUserName("Davy", pageable);
        List<User> list2 = all2.getContent();
        System.out.println(list2);

        System.out.println(userRespository.findByIdRange(1l, 5l));


        //多表联查
        Page<UserDetail> all3 = userRespository.findAllName(pageable);
        List<UserDetail> list3 = all3.getContent();
        System.out.println(list3);

        UserDetail userDetail = userRespository.findByName("Davy");
        System.out.println(userDetail);
    }
}
