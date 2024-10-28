package com.yly.algorithm.leetcode.用两个栈实现队列;

import java.util.Stack;

/**
 * 这也太简单了吧
 */
public class 用两个栈实现队列 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack2.push(node);
    }

    public int pop() {
        if (stack1.empty()) {
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
            return stack1.pop();
        } else {
            return stack1.pop();
        }
    }
}
