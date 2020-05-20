package com.test.class_relationship;

import org.junit.Test;

public class ReflectTest {
    @Test
    public void test() throws IllegalAccessException, InstantiationException {
        Father f = Father.class.newInstance();
        f.fatherSay();
    }
}
