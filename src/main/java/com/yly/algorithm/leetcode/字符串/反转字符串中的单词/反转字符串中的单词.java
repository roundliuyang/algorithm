package com.yly.algorithm.leetcode.字符串.反转字符串中的单词;

public class 反转字符串中的单词 {
    public String reverseWords(String s) {
        String[] strs = s.split(" "); // 删除首尾空格，分割字符串
        StringBuilder res = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) { // 倒序遍历单词列表
            if (strs[i].equals("")) {
                continue; // 遇到空单词则跳过
            }
            res.append(strs[i]).append(" "); // 将单词拼接至 StringBuilder
        }
        return res.toString().trim(); // 转化为字符串，删除尾部空格，并返回
    }

//    public String reverseWords2(String s) {
//        s = s.trim(); // 删除首尾空格
//        int j = s.length() - 1, i = j;
//        StringBuilder res = new StringBuilder();
//        while (i >= 0) {
//            while (i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
//            res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
//            while (i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
//            j = i; // j 指向下个单词的尾字符
//        }
//        return res.toString().trim(); // 转化为字符串并返回
//    }
}
