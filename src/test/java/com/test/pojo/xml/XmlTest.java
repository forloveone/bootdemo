package com.test.pojo.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("TCRMService")
public class XmlTest {
    @XStreamAsAttribute
    @XStreamAlias("xmlns")
    private String xmlns;
    @XStreamAsAttribute
    @XStreamAlias("xmlns:xsi")
    private String xsi;
    @XStreamAsAttribute
    @XStreamAlias("xsi:schemaLocation")
    private String schemaLocation;

    @XStreamAlias("RequestControl")
    private Head head;
    @XStreamAlias("TCRMInquiry")
    private RequestParamAndMethod param;
}
