package com.yly.algorithm.单调栈;

import java.util.Stack;

/**
 * 给出一个只包含'(' 和')'的字符串，找出其中最长的左右括号正确匹配的合法子串。
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 * "(()()))"
 * 6
 * <p>
 * ")()()))"
 * 4
 */
public class 最长有效括号 {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}
