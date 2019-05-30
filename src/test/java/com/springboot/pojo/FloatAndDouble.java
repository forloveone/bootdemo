package com.springboot.pojo;

import com.springboot.data.enums.EnumJSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloatAndDouble {
    private float f;
    private double d;
    private Float ff;
    private Double dd;
    //短暂的 不会被序列化和反序列化
    private transient String test;

    private EnumJSON str = EnumJSON.FRIDAY;
}
