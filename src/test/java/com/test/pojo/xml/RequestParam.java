package com.test.pojo.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("tcrmParam")
@XStreamConverter(RequestParamConverter.class)
public class RequestParam {

    private String nameVal;

    private String val;
}
