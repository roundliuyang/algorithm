package com.yly.algorithm.动态规划.最大子数组和;

public class 最大子数组和 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            //两种情况更新 dp[i]
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            //更新 max
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
