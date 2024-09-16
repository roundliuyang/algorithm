package com.yly.algorithm.动态规划.打劫房屋;

public class 打劫房屋 {
    public long houseRobber(int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = a[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + a[i - 1]);
        }
        return dp[n];
    }
}
