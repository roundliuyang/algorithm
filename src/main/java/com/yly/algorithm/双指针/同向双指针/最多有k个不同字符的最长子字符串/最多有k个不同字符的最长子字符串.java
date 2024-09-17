package com.yly.algorithm.双指针.同向双指针.最多有k个不同字符的最长子字符串;


import java.util.HashMap;

public class 最多有k个不同字符的最长子字符串 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0;
        int right = 0;
        int n = s.length();
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while (right < n) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

            // 如果窗口内的不同字符数量超过 k，则移动左指针直到窗口内的不同字符数量不超过 k
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }
            // 更新结果
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
