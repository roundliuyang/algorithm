package com.yly.algorithm.leetcode;

import java.util.HashMap;

public class Test {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int left = 0;
        int right = 0;
        int n = s.length();
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        if(s.length() ==0 || k ==0){
            return 0;
        }


        while (left < n && right < n) {
            if (!map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);
                if (map.size() <= k) {
                    res = Math.max(res, right - left + 1);
                    right++;
                } else {
                    map.put(s.charAt(left), map.getOrDefault(s.charAt(left), 0) - 1);
                    if (map.get(s.charAt(left)).equals(0)) {
                        map.remove(s.charAt(left));
                    }
                    left++;
                }
            } else {
                if (map.size() <= k) {
                    res = Math.max(res, right - left + 1);
                    right++;
                }else{
                    left++;

                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.lengthOfLongestSubstringKDistinct("eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh",16);
    }
}
