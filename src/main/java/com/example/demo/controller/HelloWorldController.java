package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class HelloWorldController {

    @Value("${com.title}")
    private String title;//use application.properties

    @Value("${com.description}")
    private String description;

    @Autowired
    private UserRespository userRespository;

    @RequestMapping("/hello")
    public String index() {
        return "<h1>Hello World,啦啦啦</h1>";
    }

    @RequestMapping("/getUser")
    public User getUser(){
        User user=new User();
        user.setId(2);
        user.setUserName("大头");
        user.setPassword("admin");
        return user;
    }

    @RequestMapping("/getUserByName")
    @Cacheable(value = "user-key")  //使用redis缓存注解，把结果存入缓存中，value的值就是缓存在redis中的key
    public User getUserByName(){
        User user=userRespository.findByUserName("Tom");
        System.out.println("若下面没出现<<无缓存的时候调用>>字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/uid")
    public String uid(HttpSession session){
        UUID uid=(UUID)session.getAttribute("uid"); //是用UUID来生成唯一的id码，改码的原理和时间和网卡有关
        if(uid==null){
            uid=UUID.randomUUID();
        }
        System.out.println(session.getCreationTime()); //得到session创建的时间
        session.setAttribute("uid",uid);
        return session.getId();
    }

    @RequestMapping("/properties")
    public String getProperties(){
        return title+":"+description;
    }
}
