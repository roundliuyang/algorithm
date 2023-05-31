package com.yly.algorithm.双指针.同向双指针;


import java.util.HashMap;

/*
    简单修改一两行代码即可解出至少包含 k 个不同字符的最长/最短子串
    给定一个仅包含小写字母的字符串 S.返回 S 中至少包含 k 个不同字符的子串的数量.
    输入：
    • S = "abcabcabca"， k = 4
    • 输出： 0
    • 输入：
    • S = "abcaac"， k = 2
    • 输出： 14
 */
public class 至少K个不同字符的子串的数量 {

    /*
        时空复杂度
        使用同向双指针：总时间复杂度O(n),空间复杂度O(|s|),其中 |s| 为字符串 s 中的 不同字符数量
     */
    public long KDistinctCharacters(String s, int k) {
        if (s.length() == 0) {
            return 0;
        }
        int n = s.length();
        long numOfSubStrings = 0;
        int differentChars = 0;
        HashMap<Character, Integer> counter = new HashMap<>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && differentChars < k) {
                Integer numOfThisChar = counter.getOrDefault(s.charAt(j), 0);
                counter.put(s.charAt(j), numOfThisChar + 1);
                if (counter.get(s.charAt(j)) == 1) {
                    differentChars++;
                }
                j++;
            }
            if (differentChars == k) {
                numOfSubStrings += n - j + 1;
            }
            counter.put(s.charAt(i), counter.get(s.charAt(i)) - 1);
            if (counter.get(s.charAt(i)) == 0) {
                differentChars--;
            }
        }
        return numOfSubStrings;
    }

}
