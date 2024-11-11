package com.yly.algorithm.leetcode.替换空格;


/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串
 * 为We%20Are%20Happy。
 */
public class 替换空格 {
    public String replaceSpace(StringBuffer str) {
        String st = str.toString();
        StringBuffer buffer = new StringBuffer();
        char[] s = st.toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                buffer.append("%20");
            } else {
                buffer.append(s[i]);
            }
        }
        return buffer.toString();
    }
}
