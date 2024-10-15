package com.yly.algorithm.面试日常.进制转换;

public class 十进制到八进制的转换 {
    
    public String test(int num) {
        int rem;
        String str = "";
        char dig[] = {'0', '1', '2', '3', '4', '5', '6', '7'};
        while (num > 0) {
            rem = num % 8;
            str = dig[rem] + str;
            num = num / 8;
        }
        return str;
    }
}
