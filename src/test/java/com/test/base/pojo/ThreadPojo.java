package com.test.base.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ThreadPojo {
    ThreadLocal<Map> threadPrivateMap = new ThreadLocal<>();
    private String name;
}
