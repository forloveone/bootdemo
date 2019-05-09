package com.springboot.genericity;

import org.junit.Test;

/**
 * 泛型类和泛型方法
 */
public class GenericityTest<U> {

    /**
     *泛型方法的参数和返回值可以不同
     */
    public <T,K> K test(T a){
        return (K) (Integer.valueOf((String) a));
    }
    //普通方法
    public U test2(U a){
        return a;
    }

    /**
     *  泛型 接 反射 可以做很多东西
     */
    public <T> T getxxxx(String key, Class<T> classOfT) throws IllegalAccessException, InstantiationException {
        return (T) classOfT.newInstance();
    }

    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        GenericityTest<Integer> g = new GenericityTest<Integer>();
        String a = "23";
        int test = g.<String, Integer>test(a);
        System.out.println(test);
        int test2 = g.test2(66);
        System.out.println(test2);

        GenericityTest t = getxxxx(null,GenericityTest.class);
        System.out.println(g.hashCode()+"  "+t.hashCode());
    }
}
