package com.yly.algorithm.leetcode.字符串.字符串中最后一个单词的长度;

/**
 * 直接从最后一个字符往前遍历，遇到空格停止就可以了。不过在此之前要过滤到末尾的空格。
 */
public class 字符串中最后一个单词的长度 {
    public int lengthOfLastWord(String s) {
        int count = 0;
        int index = s.length() - 1;
        //过滤空格
        while (true) {
            if (index < 0 || s.charAt(index) != ' ')
                break;
            index--;
        }
        //计算最后一个单词的长度
        for (int i = index; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            }
            count++;
        }
        return count;
    }
}
