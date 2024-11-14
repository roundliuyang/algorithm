package com.yly.algorithm.单调栈.逆波兰表达式求值;

import java.util.Stack;

public class 逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            // 遍历每个字符串，如果遇到四种运算符就将栈顶前两个元素做对应的运算
            if (s.equals("+")) {
                // 将栈顶前两个元素做“+”运算后的结果再压入栈中
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                int num1 = stack.pop();
                stack.push(stack.pop() / num1);
            } else {
                // 如果不是运算符，就将该字符串拆箱成 int 后入栈
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }
}
