package com.springboot.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ThreadTest {
    ThreadLocal<Map> local = new ThreadLocal<>();
    private String name;
}
