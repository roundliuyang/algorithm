package com.yly.algorithm.单调栈.有效的括号;

import java.util.HashMap;
import java.util.Stack;

public class 有效的括号 {
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        int n = s.length();
        Stack<Character> stack = new Stack();
        for(int i = 0; i < n; i++){
            if(map.containsKey(s.charAt(i))){
                if(stack.isEmpty() || stack.peek() != map.get(s.charAt(i))){
                    return false;
                }
                stack.pop();
            }
            else{
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}
