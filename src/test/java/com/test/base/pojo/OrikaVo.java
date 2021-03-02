package com.test.base.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 展示对象 XXVO xx为网页名称
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrikaVo {
    private String nameVo;
    private int ageVo;

    private String address;
}
