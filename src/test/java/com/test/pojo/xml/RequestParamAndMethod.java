package com.test.pojo.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestParamAndMethod {
    @XStreamAlias("InquiryType")
    private String method;
    @XStreamAlias("InquiryParam")
    private List<RequestParam> param;
}
