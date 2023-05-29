package com.yly.algorithm.动态规划;


import java.util.HashMap;
import java.util.Map;


public class 爬楼梯 {


    /* 动态规划五部曲：
     * 1.确定dp[i]的下标以及dp值的含义： 爬到第i层楼梯，有dp[i]种方法；
     * 2.确定动态规划的递推公式：dp[i] = dp[i-1] + dp[i-2];
     * 3.dp数组的初始化：因为提示中，1<=n<=45 所以初始化值，dp[1] = 1, dp[2] = 2;
     * 4.确定遍历顺序：分析递推公式可知当前值依赖前两个值来确定，所以递推顺序应该是从前往后；
     * 5.打印dp数组看自己写的对不对；
     */


    /**
     * 解法1（最容易想到的，也是最简单的）
     * 但是不优化的话很容易超时
     *
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        if (n == 1 || n == 2) return n;
        int res = climbStairs1(n - 1) + climbStairs1(n - 2);
        return res;
    }


    /**
     * 解法2，针对1做记忆优化
     */
    Map<Integer, Integer> stepCache = new HashMap<>();

    public int climbStairs2(int n) {
        if (n == 1 || n == 2) return n;

        if (stepCache.containsKey(n)) {
            return stepCache.get(n);
        }
        int res = climbStairs2(n - 1) + climbStairs2(n - 2);
        stepCache.put(n, res);
        return res;
    }


    /**
     * for循环 ，从下往上
     *
     * @param n
     * @return
     */
    int climbStairs3(int n) {
        if (n <= 1)
            return n;

        // Define dp array
        int[] dp = new int[n + 1];

        // Initialize dp array
        dp[1] = 1;
        dp[2] = 2;

        // Iterate from 3 to n
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
