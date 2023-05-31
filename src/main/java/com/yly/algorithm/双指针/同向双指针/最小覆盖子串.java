package com.yly.algorithm.双指针.同向双指针;

import java.util.HashMap;

/*  leetcode:76
    给定两个字符串 source 和 target ,求 source 中最短的包含 target 中每一个字符的子串
    • 输入: source = "abc"， target = "ac"
    • 输出: "abc"
    • 输入: source = "abcdadcda"， target = "accb"
    • 输出: "bcdadc"
 */
public class 最小覆盖子串 {
    /*
        时空复杂度
        时间复杂度：O(n + m)
        空间复杂度：O(|target| + |source|)
        n, m 为两串长度
        |target|，|source| 为两串字符集大小
    */
    public String minWindow(String source, String target) {
        if (target.length() == 0 || source.length() == 0) {
            return "";
        }
        int m = target.length();
        int n = source.length();
        // targetCounter 存放target 中的每种字符数量
        // subCounter 存放双指针对应子串中的每种字符数量
        HashMap<Character, Integer> targetCounter = new HashMap<>();
        HashMap<Character, Integer> subCounter = new HashMap<>();

        for (int i = 0; i < m; i++) {
            Integer numOfThisChar = targetCounter.getOrDefault(target.charAt(i), 0);
            targetCounter.put(target.charAt(i), numOfThisChar+1);
        }

        int j = 0;
        // matchedChars 存放匹配上的字符数量
        int matchedChars = 0;
        int start = 0;
        int subStringLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // j 指针前移条件； j< n 并且匹配数量不足
            while (j < n && matchedChars < targetCounter.size()) {
                // +1 后恰好相等，匹配数量加1
                Integer numOfThisChar = subCounter.getOrDefault(source.charAt(j), 0);
                subCounter.put(source.charAt(j), numOfThisChar + 1);

                // java 的 HashMap 中存放的是 Integer 对象，应使用 equals 比较值
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
            Integer numOfThisChar = subCounter.getOrDefault(source.charAt(i), 0);
            subCounter.put(source.charAt(i), numOfThisChar - 1);
            // java 的HashMap 中存放的是 Integer 对象，应使用 equals 比较值
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
