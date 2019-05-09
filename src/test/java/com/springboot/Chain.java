package com.springboot;

import org.junit.Test;

/**
 * 链式编程
 */
public class Chain {
    private String name;
    private String address;
    private String idCard;

    public String getName() {
        return name;
    }

    public Chain setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Chain setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public Chain setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    @Override
    public String toString() {
        return "Chain{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }

    @Test
    public void test(){
        Chain c = new Chain();
        c.setAddress("地球").setIdCard("409097877656").setName("链式编程");
        System.out.println(c);
    }
}
