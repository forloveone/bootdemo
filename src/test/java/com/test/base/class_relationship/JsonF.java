package com.test.base.class_relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonF {
    private String fName;
    private int fAge;

    private String type;

    public String faddress;
}
