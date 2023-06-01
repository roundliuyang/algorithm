package com.yly.algorithm.单调栈;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 * 输入：[0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * <p>
 * 解法四：特殊解法：单调栈
 * 一个数字从单调栈中被弹出后
 * 即将入栈的数字，弹出的数字，新的栈顶 三者形成凹陷
 * 相当于将这处凹陷 用“水”铺满，铺水的宽度就是凹陷的宽度
 * 铺水的高度就是两端点的高度最小值减去凹陷的高度
 * 可类比“直方图的最大矩形覆盖”一题
 * <p>
 * 复杂度分析
 * 单调栈的时间复杂度 O(n)
 * 最坏情况栈的大小为 n，空间复杂度 O(n)
 */
public class 接雨水 {
    int trap(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int n = heights.length;
        int waterArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                int pop = stack.pop();
                // 如果栈空，说明所有的水都从左边流走了
                if (!stack.isEmpty()) {
                    // 左右端点不计入注水宽度
                    // 相当于 i -stack() + 1 -2
                    int width = i - stack.peek() - 1;
                    // 注水高度为两个端点高度的较小值减去最低点的高度
                    int height = Math.min(heights[stack.peek()], heights[i]);
                    waterArea += width * height;
                }
            }
            stack.push(i);
        }
        return -waterArea;
    }
}

 