package com.example.demo.domain;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


/*
实体对应的mapper类的配置，用于编辑sql语句,当字段名和数据库中的名字不一样时注意配置
 */
@Component(value="userMapper")
public interface UserMapper {

    @Select("select * from user")
    @Results({@Result(property = "userName",column = "user_name")})//字段名不同时的设置
    List<User> getAll();

    @Select("select * from user where id=#{id}")
    @Results({@Result(property = "userName",column = "user_name")})
    User getOne(Long id);

    @Update("Update user set user_name=#{userName},password=#{password} where id=#{id}")//注意数据库中的字段名和实体的字段名
    void update(User user);

    @Insert("insert into user(user_name,password) values(#{userName},#{password})")
    void insert(User user);

    @Delete("delete from user where id=#{id}")
    void delete(Long id);
}
