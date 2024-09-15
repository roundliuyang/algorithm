package com.yly.algorithm.DFS.生成括号;

import java.util.ArrayList;
import java.util.List;


public class 生成括号 {

    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        List<String> sequence = new ArrayList<>();
        dfs(n, sequence, 0, 0, results);
        return results;
    }

    private void dfs(int n, List<String> sequence, int leftParen, int rightParen, List<String> results) {
        if (sequence.size() == 2 * n) {
            results.add(String.join("", sequence));
            return;
        }
        if (leftParen < n) {
            sequence.add("(");
            dfs(n, sequence, leftParen + 1, rightParen, results);
            sequence.remove(sequence.size() - 1);
        }
        if (rightParen < leftParen) {
            sequence.add(")");
            dfs(n, sequence, leftParen, rightParen +1, results);
            sequence.remove(sequence.size() - 1);
        }
    }
}
