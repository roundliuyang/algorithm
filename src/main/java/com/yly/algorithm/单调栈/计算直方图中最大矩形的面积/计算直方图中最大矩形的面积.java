package com.yly.algorithm.单调栈.计算直方图中最大矩形的面积;

import java.util.Stack;

public class 计算直方图中最大矩形的面积 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 如果当前高度大于栈顶元素，则入栈
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                // 计算栈顶元素对应的矩形面积
                int height = heights[stack.pop()];
                // 如果栈为空，说明这个矩形宽度就是整个数组的宽度
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            // 将当前元素索引入栈
            stack.push(i);
        }

        // 处理栈中剩下的元素
        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }
}
