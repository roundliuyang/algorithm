package com.yly.algorithm.动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * 扔n个骰子，向上面的数字之和为S。给定n,请列出所有可能的S值及其相应的概率
 * 确定dp很关键
 */
public class n个骰子的点数 {
    public Map<Integer, Double> dicesProbability(int n) {
        HashMap<Integer, Double> res = new HashMap<>();
        double dp[][] = new double[n + 1][n * 6 + 1];   // dp[i][j]  扔了i次和为j的概率
        // 初始化
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1.0 / 6;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= i * 6; j++) {    // j  --> 扔了i次和为j
                for (int k = 1; k <= 6; k++) {
                    if (j - k > 0)                 // j -k <= 0,不用管
                        dp[i][j] += dp[i - 1][j - k] / 6;
                    else
                        break;
                }
            }
        }
        for (int i = 0; i <= 5 * n; i++) {
            res.put(n + i, dp[n][n + i]);
        }
        return res;
    }
}


/**
 * 此题的变体
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 */

/*
public double[] dicesProbability(int n) {
        double res[] = new double[n*5+1];
        double dp[][] = new double[n+1][n*6+1];
        for(int i = 1;i <= 6;i++){
            dp[1][i] = 1.0/6;
        }
        for(int i = 2;i <= n;i++){
            for(int j = i;j <= i*6;j++){
                for(int k = 1;k <= 6;k++){
                    if(j-k > 0)
                        dp[i][j] += dp[i-1][j-k]/6;
                    else
                        break;
                }
            }
        }
        for(int i = 0;i <= 5*n;i++){
            res[i] = dp[n][n+i];
        }
        return res;
    }
 */



