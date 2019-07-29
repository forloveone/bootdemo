package com.test.data.json;

import com.test.pojo.xml.*;
import com.thoughtworks.xstream.XStream;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * XStream 可以做到把xml(String) 和 pojo做转换,注解方式
 */
public class XStremTest {

    private XmlTest xmlTest;

    @Before
    public void createDate() {
        XmlTest test = new XmlTest();
        test.setXsi("http://www.w3.org/2001/XMLSchema-instance");
        test.setXmlns("http://www.ibm.com/mdm/schema");
        test.setSchemaLocation("http://www.ibm.com/mdm/schemaMDMDomains.xsd");
        Head head = new Head();
        Control control = new Control();
        control.setRequesterLanguage("400");
        control.setRequesterName("TEl");
        head.setRequestId("20170418140322");
        head.setControl(control);
        test.setHead(head);
        RequestParamAndMethod method = new RequestParamAndMethod();
        List list = new ArrayList();
        RequestParam param = new RequestParam();
        param.setVal("10");
        param.setNameVal("MaxReturn");
        list.add(param);
        method.setMethod("getPartyByDetail");
        method.setParam(list);
        test.setParam(method);
        this.xmlTest = test;
    }

    @Test
    public void xstream() {
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        String xml = xstream.toXML(xmlTest);
        System.out.println(xml);
        XmlTest test2 = (XmlTest) xstream.fromXML(xml);
        System.out.println(test2);
    }
}
