package com.yly.algorithm.单调栈;

    /*
        一位店主需要完成一项销售任务，他将要出售的物品排成一排。
        从左侧开始，店主以其全价减去位于该物品右侧的第一个价格较低或价格相同的商品的价格。
        如果右侧没有价格低于或等于当前商品价格的商品，则以全价出售当前商品。
        你需要返回每一个物品实际售出价格。
        输入：[2, 3, 1, 2, 4, 2]
        输出：[1, 2, 1, 0, 2, 2]
     */

import java.util.Arrays;
import java.util.Stack;

public class 最终优惠价 {

    /**
     * 解法一：暴力法
     * 遍历整个数组，再遍历每个数字的右边的所有数字，找到第一个更小的值，记录下来即可
     */

    public int[] finalDiscountedPrice(int[] prices) {
        int[] results = Arrays.copyOf(prices, prices.length);
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    results[i] = prices[i] - prices[j];
                    break;
                }
            }
        }
        return results;
    }

    /**
     * 解法二：单调栈
     */

    public int[] finalDiscountedPrice2(int[] prices) {
        int[] results = Arrays.copyOf(prices, prices.length);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                results[stack.peek()] = prices[stack.peek()] - prices[i];
                stack.pop();
            }
            stack.push(i);
        }
        return results;
    }
}
