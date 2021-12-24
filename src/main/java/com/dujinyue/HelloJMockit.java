package com.dujinyue;

import lombok.Data;

import java.util.Locale;

@Data
public class HelloJMockit {
    private int i;

    public static int j;

    // 初始代码块
    {
        i = 1;
    }

    // 静态初始代码块
    static {
        j = 2;
    }

    public HelloJMockit(int i) {
        this.i = i;
    }

    public String sayHello() {
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA)) {
            // 在中国，就说中文
            return "你好，JMockit!";
        } else {
            // 在其它国家，就说英文
            return "Hello，JMockit!";
        }
    }
}
