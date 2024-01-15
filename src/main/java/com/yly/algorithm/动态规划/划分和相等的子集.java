package com.yly.algorithm.动态规划;

/**
 * 给⼀ 只含有正整数 的 ⾮空 数组, 找到这个数组是否可以划分为 两个 元素和相等的⼦集。
 * • 输⼊: nums = [1, 5, 11, 5],
 * • 输出: true
 * • 解释:
 * • two subsets: [1, 5, 5], [11]
 */
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
