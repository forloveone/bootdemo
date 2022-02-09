package com.test.base.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GsonPojo2 {
    //实体类属性和json属性名不一致的情况  反序列化的时候如果两个都有,则后面的那个赋值会生效
    @SerializedName(value = "teacherName", alternate = "userName")
    //实现字段过滤 默认都是true
    @Expose(serialize = true, deserialize = true)
    private String accountNo;
    @Expose(serialize = false, deserialize = false)
    private String password;
    @Expose(serialize = true, deserialize = false)
    private String name;
    @Expose(serialize = false, deserialize = true)
    private int age;

    private String dateBirth;

    private String date_birth;
}
