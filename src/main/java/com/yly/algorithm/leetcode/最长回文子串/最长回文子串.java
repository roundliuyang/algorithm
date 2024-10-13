package com.yly.algorithm.leetcode.最长回文子串;

/**
 * 给出一个字符串（假设长度最长为1000），求出它的最长回文子串，你可以假定只有一个满足条件的最长回文串。
 */
public class 最长回文子串 {
    // 暴力解法
    public String longestPalindrome(String s) {
        int n = s.length();
        String res = "";
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isHuiwen(s.substring(i, j))) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        res = s.substring(i, j);
                    }
                }
            }
        }
        return res;
    }

    // 判断是否回文
    private boolean isHuiwen(String s) {
        int len = s.length();
        for (int i = 0; i < len/2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
