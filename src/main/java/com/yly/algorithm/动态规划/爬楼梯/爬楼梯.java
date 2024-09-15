package com.yly.algorithm.动态规划.爬楼梯;


import java.util.HashMap;
import java.util.Map;


public class 爬楼梯 {
    
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
