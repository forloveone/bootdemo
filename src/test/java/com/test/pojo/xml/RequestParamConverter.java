package com.test.pojo.xml;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class RequestParamConverter implements Converter {

    /**
     * 将java对象转为xml时使用
     */
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        RequestParam attr = (RequestParam) source;
        // writer.startNode("attribute");
        writer.addAttribute("name", attr.getNameVal());
        writer.setValue(attr.getVal());
        // writer.endNode();
    }
    /**
     * 将xml转为java对象使用
     */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        RequestParam a = new RequestParam();// 在解析attribute元素时，先创建一个CarAttr对象
        a.setNameVal(reader.getAttribute("name"));// 将attribute元素的name属性设置为CarAttr对象的name属性值
        a.setVal(reader.getValue());// 将attribute元素的txt值设置为CarAttr对象的value值
        return a;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(RequestParam.class);//转换条件;
    }
}
