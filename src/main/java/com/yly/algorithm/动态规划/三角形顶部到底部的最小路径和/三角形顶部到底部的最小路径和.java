package com.yly.algorithm.动态规划.三角形顶部到底部的最小路径和;

import java.util.List;


/**
 * 自底向上, DP
 */
public class 三角形顶部到底部的最小路径和 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();

        // 使用一维数组来存储最小路径和
        int[] dp = new int[row];

        // 初始化底部行
        for (int j = 0; j < triangle.get(row - 1).size(); j++) {
            dp[j] = triangle.get(row - 1).get(j);
        }

        // 从倒数第二行开始，逐步更新 dp 数组
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        // 返回顶部的最小路径和
        return dp[0];
    }
}
