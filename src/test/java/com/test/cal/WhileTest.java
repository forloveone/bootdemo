package com.test.cal;

import java.util.Scanner;

public class WhileTest {

    public static void main(String[] args) {
        System.out.println("请选择你喜欢的运动");
        System.out.println("1.足球");
        System.out.println("2.篮球");
        System.out.println("3.羽毛球");
        System.out.println("4.乒乓球");
        Scanner xx = new Scanner(System.in);
        int choice = xx.nextInt();
        //死循环的应用
        while (true) {
            if (choice == 1) {
                System.out.println("你喜欢足球");
                break;
            } else if (choice == 2) {
                System.out.println("你喜欢篮球");
                break;
            } else if (choice == 3) {
                System.out.println("你喜欢羽毛球");
                break;
            } else {
                System.out.println("你输入的有误,请重新输入!");
                choice = xx.nextInt();
            }
        }
    }
}
