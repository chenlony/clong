package com.demo.test;


public class 位运算 {
    public static void main(String[] args) {
        //给你一组整型数据，这些数据中，其中有一个数只出现了一次，其他的数都出现了两次，让你来找出一个数 。
        
        int x = 1^2^1;
        System.out.println(x);
        
        //交换
        int a= 1;
        int b = 2;
        a = a ^ b;//01   10  a = 11
        b = a ^ b;//11   10  b = 01
        a = a ^ b;//11   01  a = 10 
        System.out.println(a+"---"+b);
              
                
    }
    
}
