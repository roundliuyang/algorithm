package com.yly.algorithm.位运算.不用加号的加法;

public class 不用加号的加法 {
    public int add(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = (a ^ b);
            b = carry;
        }
        return a;
    }
}