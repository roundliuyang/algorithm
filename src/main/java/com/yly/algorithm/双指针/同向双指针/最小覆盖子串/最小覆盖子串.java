package com.yly.algorithm.双指针.同向双指针.最小覆盖子串;

import java.util.HashMap;

public class 最小覆盖子串 {

    public String minWindow(String source, String target) {
        if (target.length() == 0 || source.length() == 0) {
            return "";
        }
        int m = target.length();
        int n = source.length();
        // targetCounter 存放target 中的每种字符数量
        HashMap<Character, Integer> targetCounter = new HashMap<>();
        // subCounter 存放双指针对应子串中的每种字符数量
        HashMap<Character, Integer> subCounter = new HashMap<>();

        for (int i = 0; i < m; i++) {
            targetCounter.put(target.charAt(i), targetCounter.getOrDefault(target.charAt(i), 0) + 1);
        }

        int j = 0;
        // matchedChars 存放匹配上的字符数量
        int matchedChars = 0;
        int start = 0;
        int subStringLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {               // 可以使用while 循环替换for循环
            // j 指针前移条件； j< n 并且匹配数量不足
            while (j < n && matchedChars < targetCounter.size()) {
                // +1 后恰好相等，匹配数量加1
                Integer numOfThisChar = subCounter.getOrDefault(source.charAt(j), 0);
                subCounter.put(source.charAt(j), numOfThisChar + 1);
                
                if (subCounter.get(source.charAt(j)).equals(targetCounter.get(source.charAt(j)))) {
                    matchedChars++;
                }
                j++;
            }

            // 达到目标匹配数量后，更新最短子串
            if (matchedChars == targetCounter.size()) {
                if (subStringLength > j - i) {
                    subStringLength = j - i;
                    start = i;
                }
            }
            // -1 后恰好差一个，匹配数量-1
            subCounter.put(source.charAt(i), subCounter.getOrDefault(source.charAt(i), 0) - 1);
            if (subCounter.get(source.charAt(i)).equals(targetCounter.getOrDefault(source.charAt(i), 0) - 1)) {
                matchedChars--;
            }


        }
        if (subStringLength == Integer.MAX_VALUE) {
            return "";
        }
        return source.substring(start, start + subStringLength);
    }
}
