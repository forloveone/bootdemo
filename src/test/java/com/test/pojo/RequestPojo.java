package com.test.pojo;

import lombok.Data;

import java.util.List;

@Data
public class RequestPojo {
    private String name;
    private String address;
    private List<Student> students;
}
