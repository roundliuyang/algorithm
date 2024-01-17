package com.yly.algorithm.双指针.同向双指针;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给出一个字符串，均为大写字母，将这个字符串划分成尽可能多的部分，使每种字母只会出现一个部分中。最后返回一个数组，这个数组包含每个部分的长度。
 * 样例 1：
 * 输入：
 * "MPMPCPMCMDEFEGDEHINHKLIN"
 * 输出：
 * [9,7,8]
 * 解释：
 * "MPMPCPMCM"
 * "DEFEGDE"
 * "HINHKLIN"
 */
public class 字符串划分 {
    public static void main(String[] args) {
        字符串划分 cla = new 字符串划分();
        System.out.println( cla.splitString("MPMPCPMCMDEFEGDEHINHKLIN"));
    }

    public List<Integer> splitString(String s) {
        Map<Character, Integer> lastPosition = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastPosition.put(s.charAt(i), i);
        }

        int i = 0;
        int j = 0;
        List<Integer> result = new LinkedList<>();
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
