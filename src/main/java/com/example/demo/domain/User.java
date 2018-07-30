package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity  //if use this annotation,you must confirm this table is in database
public class User implements Serializable {
    private static final long serialVersionUID = 2952746266349588937L;
    /*
    如果要改变枚举型数据，比如使用枚举对应String类型：使用@Enumerated(EnumType.STRING)注解

    @Column会和数据库映射，加上@Transient属性，不会和数据库的字段映射
     */

    @Id
    @GeneratedValue
    private Long id;  //id在保存时会自动生成和传入的值无关

    @Column(nullable = true)
    private String userName;
    @Column
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, password);
    }
}
