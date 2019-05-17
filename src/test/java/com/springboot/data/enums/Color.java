package com.springboot.data.enums;

//枚举都继承自java.lang.Enum类
//枚举可以实现接口
//switch 中可以应用enum
public enum Color {
    RED("红色", 1),//注释添加
    GREEN("绿色", 2),
    BLANK("白色", 3),
    YELLO("黄色", 4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public String getName(){
        return name;
    }
    public int getIndex(){
        return index;
    }
}
