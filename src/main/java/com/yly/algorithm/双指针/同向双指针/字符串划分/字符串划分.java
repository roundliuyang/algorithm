package com.yly.algorithm.双指针.同向双指针.字符串划分;


import java.util.*;

public class 字符串划分 {

    public List<Integer> splitString(String s) {
        Map<Character, Integer> lastPosition = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastPosition.put(s.charAt(i), i);
        }

        int i = 0;
        int j = 0;
        List<Integer> result = new ArrayList<>();
        while (i < s.length()) {
            int start = i;
            j = lastPosition.get(s.charAt(i));
            while (j < s.length() && i < j) {
                j = Math.max(j, lastPosition.get(s.charAt(i)));
                i++;
            }
            result.add(j - start + 1);
            i++;
        }
        return result;
    }
}
