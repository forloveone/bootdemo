package com.springboot.bussiness.pojo;

import java.util.Date;

public class User {
    private Long id;

    private String name;

    private String password;

    private Date addTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public User(String name, String password, Date addTime) {
        this.name = name;
        this.password = password;
        this.addTime = addTime;
    }

    public User() {
    }
}