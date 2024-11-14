package com.yly.algorithm.动态规划.解码方法;

public class 解码方法 {
    public int numDecodings(String s) {
        char[] ss = s.toCharArray();
        if (ss.length == 0 || ss[0] == '0')
            return 0;
        int len = ss.length;
        int[] dp = new int[ss.length + 1];

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= len; i++) {
            if (ss[i - 1] >= '1' && ss[i - 1] <= '9') {
                dp[i] += dp[i - 1];
            }
            if (ss[i - 2] == '1' || (ss[i - 2] == '2' && ss[i - 1] >= '0' && ss[i - 1] <= '6')) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }
}
