package com.yly.algorithm.双指针.相向双指针;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 * 双指针解法
 * <p>
 * 输入：[0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * <p>
 * <p>
 * 解法一：暴力法（忽略）
 * 对于每个数字，找到他两侧的两个最大值两个最大值的较小值就是这个格子存水的高度
 * 复杂度分析每个数字都要计算结果 O(n)
 * 向左右找到两个最小值 O(n)
 * 总时间复杂度 O(n ^ 2)
 * 空间复杂度 O(1)
 * <p>
 * <p>
 * <p>
 * 解法二：双指针
 */
public class 接雨水 {
    int trap(int[] height) {
        if (height.length == 0) return 0;
        int n = height.length;
        int left = 0, right = n - 1;
        int ans = 0;

        int l_max = height[0];
        int r_max = height[n - 1];

        while (left <= right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // ans += min(l_max, r_max) - height[i]
            if (l_max < r_max) {
                ans += l_max - height[left];
                left++;
            } else {
                ans += r_max - height[right];
                right--;
            }
        }
        return ans;
    }
}

 