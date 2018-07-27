package com.example.demo.controller;

import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Value("${com.title}")
    private String title;//use application.properties

    @Value("${com.description}")
    private String description;

    @RequestMapping("/hello")
    public String index() {
        return "<h1>Hello World,啦啦啦</h1>";
    }

    @RequestMapping("/getUser")
    public User getUser(){
        User user=new User();
        user.setUserName("大头");
        user.setPassword("admin");
        return user;
    }

    @RequestMapping("/properties")
    public String getProperties(){
        return title+":"+description;
    }
}
