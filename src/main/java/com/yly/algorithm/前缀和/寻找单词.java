package com.yly.algorithm.前缀和;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串str，和一个字典dict，你需要找出字典里的哪些单词是字符串的子序列，返回这些单词。返回单词的顺序应与词典中的顺序相同。
 * <p>
 * 例1:
 * 输入:
 * str="bcogtadsjofisdhklasdj"
 * dict=["book","code","tag"]
 * 输出:
 * ["book"]
 * 解释:只有book是str的子序列
 */
public class 寻找单词 {

    /**
     * 你丫的我上来就是暴力求解
     */
    public List<String> findWords(String str, List<String> dict) {
        List<String> res = new ArrayList<>();
        for (String word : dict) {
            if (match(str, word)) {
                res.add(word);
            }
        }
        return res;
    }
    private boolean match(String str, String word){
        int bs = 0;
        int bw = 0;

        while(bs < str.length() && bw < word.length()){
            if(str.charAt(bs) == word.charAt(bw)){
                bw++;
            }
            bs++;
        }
        return bw == word.length();
    }
}
