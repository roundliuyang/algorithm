package com.yly.algorithm.动态规划.爬楼梯扩展问题;

public class 爬楼梯扩展问题 {
    public int jumpFloorII(int target) {
        int[] dp = new int[target + 1];
        //初始化前面两个
        dp[0] = 1;
        dp[1] = 1;
        //依次乘2
        for (int i = 2; i <= target; i++)
            dp[i] = 2 * dp[i - 1];
        return dp[target];
    }
}
