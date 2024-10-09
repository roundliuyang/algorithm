package com.yly.algorithm.leetcode;

public class 字符串中连续重复次数最多的字符 {
    public static void test(String s) {
        char maxChar = s.charAt(0);
        int maxCount = 1;

        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                count = 1;
            }

            if (count > maxCount) {
                maxCount = count;
                maxChar = s.charAt(i);
            }
        }

        System.out.println(maxChar);
        System.out.println(maxCount);
    }
}
