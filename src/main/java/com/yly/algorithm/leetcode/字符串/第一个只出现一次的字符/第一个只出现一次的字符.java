package com.yly.algorithm.leetcode.字符串.第一个只出现一次的字符;

import java.util.HashMap;

/**
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母
 * 哈希表
 */

public class 第一个只出现一次的字符 {
    public char firstUniqChar(String s) {
        HashMap<Character, Boolean> dic = new HashMap<>();
        char[] sc = s.toCharArray();
        for (char c : sc){
            dic.put(c, !dic.containsKey(c));
        }
            
        for (char c : sc){
            if (dic.get(c)){
                return c;
            }
        }
        return ' ';
    }
}
