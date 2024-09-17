package com.yly.algorithm.双指针.同向双指针.至少K个不同字符的子串的数量;


import java.util.HashMap;

public class 至少K个不同字符的子串的数量 {
    
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
                counter.put(s.charAt(j), counter.getOrDefault(s.charAt(j), 0) + 1);
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
