package com.example.demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRespository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    User findById(Long id);

    List<User> findAll();

    //分页查询会获得Page对象，通过遍历获取里面的值,或使用page.getContent()获取list<T>对象
    //Page从第0页开始
    Page<User> findByUserName(String userName, Pageable pageable);

    /*
    自定义查询语句sql在SQL的查询方法上面使用@Query注解，如涉及到删除和修改在需要加上@Modifying.
        也可以根据需要添加 @Transactional 对事物的支持，查询超时的设置等
    注意语句中选择"select * "改成"select u from User u",param要用?1,?2表示位置
    */
    @Modifying
    @Query("update User u set u.userName=?1 where u.id=?2")
    int modifyByIdAndUserName(String userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id=?1")
    void deleteByUserId(Long id);

    @Transactional(timeout = 10)
    @Query("select u from User u where u.id>?1 and u.id<?2")
    List<User> findByIdRange(Long idstart, Long idend);

    //多表联查。方法一:sql查询的是实体类型，而不是数据库表
    @Query(value = "SELECT new com.example.demo.domain.UserDetail(u, c) FROM com.example.demo.domain.User u, com.example.demo.domain.Customer c WHERE u.userName = c.name")
    Page<UserDetail> findAllName(Pageable pageable);

    //多表联查。方法一:sql查询的是实体类型，而不是数据库表
    @Query(value = "SELECT new com.example.demo.domain.UserDetail(u, c) FROM com.example.demo.domain.User u, com.example.demo.domain.Customer c WHERE u.userName = c.name and c.name=?1")
    UserDetail findByName(String name);

}
