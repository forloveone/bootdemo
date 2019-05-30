package com.springboot.class_relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonS extends JsonF{
    private String sName;
    private int sAge;
}
