package com.test.base.data;

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

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }


    public static void main(String[] args) {
        System.out.println(Color.BLANK);
        System.out.println(Color.BLANK.getIndex());
        System.out.println(Color.BLANK.getName());

        Color[] values = Color.values();
        for (Color c : values) {
            System.out.println(c.getIndex());
            System.out.println(c.getName());
        }
    }
}
