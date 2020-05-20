package com.springboot.bussiness.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestPojo implements Serializable {
    private int age;
    private double num;
    private String name;
    private Date date;
    private BigDecimal bignum;
}
