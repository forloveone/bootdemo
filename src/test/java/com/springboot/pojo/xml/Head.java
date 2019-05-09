package com.springboot.pojo.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Head {
    @XStreamAlias("requestID")
    private String requestId;
    @XStreamAlias("DWLControl")
    private Control control;
}
