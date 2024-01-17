package com.yly.algorithm.动态规划;

public class 买卖股票的最佳时机 {

    /**
     * 暴力法
     */
    public int maxProfit(int prices[]) {
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
     * 这是一种贪心算法的思路，通过在一次遍历中不断更新最小值和最大利润，来得到最优解
     */
    public int maxProfit2(int prices[]) {
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }


    /**
     * 动态规划
     */
    public int maxProfit3(int prices[]) {
        return 1;
    }


}
