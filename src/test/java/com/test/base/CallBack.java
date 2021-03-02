package com.test.base;

/**
 * callBack java回调机制的简单实例
 * 一个类,委托另一个类,并把自己的指针传过去,这样另一个类就可以调用一个类的方法了(这个调用就叫回调)
 * <p>
 * 回调:
 * 就是A类中调用B类中的某个方法C，然后B类中反过来调用A类中的方法D，D这个方法就叫回调方法，
 */
//小红
class XiaoHong {

    public void add(int a, int b, XiaoMing xiaoming) {
        xiaoming.fillBlank(a, b, a + b);
    }
}

class XiaoMing {

    private String name = null;

    public XiaoMing(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void callHelp(int a, int b) {
        new XiaoHong().add(a, b, this);
    }

    public void fillBlank(int a, int b, int result) {
        System.out.println(name + "求助小红计算:" + a + " + " + b + " = " + result);
    }
}

class TestDemo {


    public static void main(String[] args) {
        int a = 26549;
        int b = 16487;
        XiaoMing s = new XiaoMing("小明");
        s.callHelp(a, b);
    }
}