package com.yly.algorithm.双指针.同向双指针;

import java.util.HashSet;


/**
 * 给定一个字符串，请找出其中无重复字符的最长子字符串。
 * <p>
 * 样例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 最长子串是 "abc".
 * <p>
 * 样例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 最长子串是 "b".
 */
public class 最长无重复字符的子串 {

    public int lengthOfLongestSubstring(String s) {
        // write your code here
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
