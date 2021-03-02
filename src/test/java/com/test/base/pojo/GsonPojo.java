package com.test.base.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GsonPojo {
    private int id;
    private Integer no;
    private String name;
    private Date addTime;
    private BigDecimal much;
    private boolean flag;
    private GsonPojo pojo;
    private List<RequestPojo> list;
}
