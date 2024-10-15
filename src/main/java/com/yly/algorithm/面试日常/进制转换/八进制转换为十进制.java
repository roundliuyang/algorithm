package com.yly.algorithm.面试日常.进制转换;

public class 八进制转换为十进制 {
    public static int octalToDecimal(int onum) {
        int num = 0;
        int p = 0;
        while (true) {
            if (onum == 0) {
                break;
            } else {
                int temp = onum % 10;
                num += temp * Math.pow(8, p);
                onum = onum / 10;
                p++;
            }
        }
        return num;
    }
}
