package com.springboot.design_pattern;

/**
 * 工厂模式
 * 何需要生成复杂对象的地方，都可以使用工厂方法模式
 */
public class FactoryMethod {

    public Object createXXX(String flag) {
        if (flag.equals("X")) {
            return new Object();
        } else if (flag.equals("Y")) {
            return new Object();
        } else {
            return null;
        }
    }
}
