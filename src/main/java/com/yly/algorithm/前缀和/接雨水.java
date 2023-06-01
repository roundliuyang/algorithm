package com.yly.algorithm.前缀和;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 * 双指针解法
 * 输入：[0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 */

public class 接雨水 {
    /**
     * 解法一：暴力法
     * 对于每个数字，找到他两侧的两个最大值两个最大值的较小值就是这个格子存水的高度
     * 复杂度分析每个数字都要计算结果 O(n)
     * 向左右找到两个最小值 O(n)
     * 总时间复杂度 O(n ^ 2)
     * 空间复杂度 O(1)
     */
    int trap1(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        int waterArea = 0;
        for (int i = 0; i < n; i++) {
            int rightMax = height[i];
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            int leftMax = height[i];
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }
            waterArea += Math.max(leftMax, rightMax) - height[i];
        }
        return -waterArea;
    }


    /**
     * 解法二：前缀优化法
     * 这是一种经典的空间换时间的优化思路
     * 使用前缀最小值和后缀最小值来加速算法
     * 注意：前缀优化不适用于查询子数组的最值
     * <p>
     * 复杂度分析
     * 总共遍历 3 次数组，两次遍历记录最大值，一次遍历求解
     * 总时间复杂度 O(3 * n) = O(n)
     * 总空间复杂度 O(2 * n) = O(n)
     */
    int trap2(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int waterArea = 0;
        for (int i = 0; i < n; i++) {
            waterArea += Math.min(rightMax[i], leftMax[i]) - height[i];
        }
        return waterArea;
    }
}

 