package com.example.demo;

import com.example.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() throws Exception{
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception{
        User user=new User(5,"Kitty","121");
        ValueOperations<String,User> operations=redisTemplate.opsForValue();
        operations.set("com.user",user);
        operations.set("com.use.x",user,10*60,TimeUnit.SECONDS); //设置key的值，并设定缓存时间10*60s
        Thread.sleep(1000);
        boolean exists=redisTemplate.hasKey("com.use.x");
        if(exists){
            System.out.println(redisTemplate.opsForValue().get("com.use.x"));
        }else{
            System.out.println("exists is false");
        }
    }
}
