package com.test.data.enums;

import org.junit.Test;

public class EnumTest {
    @Test
    public void enumTest() {
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
