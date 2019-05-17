package com.springboot.data.json;

import com.springboot.utils.PinYinUtil;
import org.junit.Test;

public class HanYuTest {
    @Test
    public void test() {
        String s = "中文台";
        System.out.println(PinYinUtil.toHanYuPinYin(s) + "  " + PinYinUtil.getFirstLetter(s));
    }
}

