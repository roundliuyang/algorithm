package com.yly.algorithm.动态规划.买卖股票的最佳时机II;

public class 买卖股票的最佳时机II {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 注意到上面的状态转移方程中，每一天的状态只与前一天的状态有关，而与更早的状态都无关，因此我们不必存储这些无关的状态，
     * 只需要将 dp[i−1][0] 和 dp[i−1][1] 存放在两个变量中，通过它们计算出 dp[i][0] 和 dp[i][1] 并存回对应的变量，以便于第 i+1 天的状态转移即可。
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }
}
