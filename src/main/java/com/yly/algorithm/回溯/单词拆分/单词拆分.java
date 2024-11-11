package com.yly.algorithm.回溯.单词拆分;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 单词拆分 {
    boolean[] mem;

    public boolean wordBreak(String s, List<String> wordDict) {
        this.mem = new boolean[s.length()];
        return word_Break(s, new HashSet(wordDict), 0);
    }

    public boolean word_Break(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        if (mem[start]) {
            return false;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end)) {
                return true;
            }
        }
        mem[start] = true;
        return false;
    }
}
