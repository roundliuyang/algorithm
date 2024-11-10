package com.yly.algorithm.leetcode.字符串.字符串相乘;

/**
 * num1 的第 i 位乘上 num2 的第 j 位，结果会分别对应 pos 的第 i + j 位和第 i + j + 1 位
 * 时间复杂度：O（m * n）。m，n 是两个字符串的长度
 * 空间复杂度：O（m + n）。m，n 是两个字符串的长度
 */
public class 字符串相乘 {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int n1 = num1.length();
        int n2 = num2.length();
        int[] pos = new int[n1 + n2]; //保存最后的结果
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                //相乘的结果
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                //加上 pos[i+j+1] 之前已经累加的结果
                int sum = mul + pos[i + j + 1];
                //更新 pos[i + j]
                pos[i + j] += sum / 10;
                //更新 pos[i + j + 1]
                pos[i + j + 1] = sum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos.length; i++) {
            //判断最高位是不是 0 
            if (i == 0 && pos[i] == 0) {
                continue;
            }
            sb.append(pos[i]);
        }
        return sb.toString();
    }
}
