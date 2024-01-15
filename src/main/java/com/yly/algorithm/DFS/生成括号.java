package com.yly.algorithm.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定 n，表示有 n 对括号, 请写一个函数以将其生成所有的括号组合，并返回组合结果。
 * 样例 2:
 * 输入: 2
 * 输出: ["()()", "(())"]
 */
public class 生成括号 {

    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        List<String> sequence = new ArrayList<>();
        dfs(n, sequence, 0, results);
        return results;
    }

    private void dfs(int n, List<String> sequence, int leftParen, List<String> results) {
        if (sequence.size() == 2 * n) {
            results.add(String.join("", sequence));
            return;
        }
        if (leftParen < n) {
            sequence.add("(");
            dfs(n, sequence, leftParen + 1, results);
            sequence.remove(sequence.size() - 1);
        }
        if (leftParen > sequence.size() - leftParen) {
            sequence.add(")");
            dfs(n, sequence, leftParen, results);
            sequence.remove(sequence.size() - 1);
        }
    }
}
