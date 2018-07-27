package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRespository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    User findById(Integer id);
    List<User> findAll();
}
