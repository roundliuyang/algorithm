package com.yly.algorithm.动态规划.划分和相等的子集;


public class 划分和相等的子集 {
    // 等价与背包问题，能否背到总价值的一半
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        for (int i = 0; i <= sum; i++) {
            dp[i] = false;
        }
        dp[0] = true;
        for (int i = 0; i < len; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] |= dp[j - nums[i]];
            }
        }
        return dp[sum];
    }
}
