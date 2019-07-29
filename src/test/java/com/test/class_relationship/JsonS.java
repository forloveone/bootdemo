package com.test.class_relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonS extends JsonF{
    private String sName;
    private int sAge;

    //父类的非private的属性和方法可以被子类公用
    public void test(){
        System.out.println(faddress);
    }
}
