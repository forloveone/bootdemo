package com.dujinyue.bussiness.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@Component
public class People implements Serializable {
    @Length(max = 1)
    private String name;
    @Max(100)
    @Min(1)
    private String age;
    private String address;

    private TestPojo pojo;
}
