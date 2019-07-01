package com.example.demo.domain;

import java.io.Serializable;

public class UserDetail implements Serializable {
    private String name;
    private String password;
    private String address;

    public UserDetail() {
    }

    public UserDetail(User u, Customer c) {
        this.name = u.getUserName();
        this.password = u.getPassword();
        this.address = c.getAddress();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
