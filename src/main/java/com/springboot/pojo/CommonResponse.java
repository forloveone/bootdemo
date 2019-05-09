package com.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 公共回参
 */
@Data
@AllArgsConstructor
public class CommonResponse<T> {
    private String status;
    private String message;
    private T data;
}
