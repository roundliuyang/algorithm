package com.yly.algorithm.动态规划.买卖股票的最佳时机;

public class 买卖股票的最佳时机 {

    /**
     * 暴力法
     */
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit)
                    maxprofit = profit;
            }
        }
        return maxprofit;
    }


    /**
     * 下述动态规划使用了 `dp[]` 数组，但实际上只需要保存 `dp[i-1]` 和 `minPrice` 即可，所以我们可以优化掉 `dp[]` 数组，使用两个变量来记录当前最大利润和最低价格：
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = prices[0];  // 记录历史最低价格
        int maxProfit = 0;  // 记录最大利润

        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);  // 更新最大利润
            minPrice = Math.min(minPrice, prices[i]);  // 更新最低价格
        }

        return maxProfit;
    }


    /**
     * 动态规划
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int[] dp = new int[n];  // dp[i] 表示第 i 天的最大利润
        int minPrice = prices[0];  // 记录第 0 到 i-1 天的最低价格

        dp[0] = 0;  // 第 0 天没有利润

        // 从第 1 天开始遍历
        for (int i = 1; i < n; i++) {
            // 更新 dp[i]，状态转移方程为：dp[i] = Math.max(dp[i-1], prices[i] - minPrice)
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
            // 更新历史最低价格
            minPrice = Math.min(minPrice, prices[i]);
        }

        // 返回最大利润，即 dp[n-1]
        return dp[n - 1];
    }
    
}
