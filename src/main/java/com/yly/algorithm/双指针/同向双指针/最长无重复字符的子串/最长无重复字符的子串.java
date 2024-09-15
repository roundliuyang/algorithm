package com.yly.algorithm.双指针.同向双指针.最长无重复字符的子串;

import java.util.HashSet;

public class 最长无重复字符的子串 {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int res = 0;
        HashSet<Character> set = new HashSet<>();
        int right = 0;
        int left = 0;
        while (right < n && left < n) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                res = Math.max(res, right - left + 1);
                right++;
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return res;
    }
}
