package com.springboot.pojo.pojosort;

import java.util.Comparator;


public class ComparePersonByAgeAsc implements Comparator<Person2> {
    //根据第一个参数小于、等于或大于第二个参数分别返回负整数、零或正整数。
    @Override
    public int compare(Person2 o1, Person2 o2) {
        if (o1.getAge()<o2.getAge()){
            return -1;
        }
        if (o1.getAge()>o2.getAge()){
            return 1;
        }
        return 0;
    }
}
