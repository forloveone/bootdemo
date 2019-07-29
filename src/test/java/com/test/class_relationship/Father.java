package com.test.class_relationship;

/**
 *  单独创建父类 和 子类创建的父类不是同一个对象
 */
class Father {
    public Father() {
        super();
        System.out.println("father create " + this.hashCode());
    }

    private static class Son extends Father {
        public Son() {
            super();
            System.out.println("son create " + this.hashCode());
        }

    }

    public static void main(String[] args) {
        Father father = new Father();
        Son son = new Son();
    }

    public void fatherSay(){
        System.out.println("fatherSay");
    }
}
