package com.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据对象 xxDo xx为表名
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrikaDo {
    private String nameDo;
    private int ageDo;

    private String address;
}
