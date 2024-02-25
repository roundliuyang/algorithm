package com.yly.algorithm.动态规划;

/**
 * 假设你是一个专业的窃贼，准备沿着一条街打劫房屋。每个房子都存放着特定金额的钱。你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，
 * 且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
 * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，在不触动报警装置的情况下, 你最多可以得到多少钱 。
 * <p>
 * 输入: [5, 2, 1, 3]
 * 输出: 8
 * 解释: 抢第一个和最后一个房子
 * <p>
 * dp[i]表示前i间房屋能偷窃到的最高总金额
 * 状态转移方程：dp[i]=max(dp[i−2]+nums[i],dp[i−1])
 */
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
