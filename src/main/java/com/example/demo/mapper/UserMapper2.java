/*
<!--使用配置文件的方式使用整合mabatis时使用-->
 */
package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value="userMapper2")
public interface UserMapper2 {
    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);
}
