package com.yly.algorithm.字符串.最长公共前缀;

public class 最长公共前缀 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        //遍历所有列
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i); // 保存 i 列第 0 行的字符便于后续比较
            //比较第 1,2,3... 行的字符和第 0 行是否相等
            for (int j = 1; j < strs.length; j++) {
                /**
                 * i == strs[j].length() 表明当前行已经到达末尾
                 * strs[j].charAt(i) != c  当前行和第 0 行字符不相等
                 * 上边两种情况返回结果
                 */
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}
