package com.yly.algorithm.动态规划.不同路径ii;

public class 不同路径ii {

    /**
     * 就是从左向右，从上到下一行一行更新
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //起点是障碍，直接返回 0 
        if (obstacleGrid[0][0] == 1)
            return 0;
        int[] dp = new int[n];
        int i = 0;
        //初始化第一行和 62 题不一样了
        //这里的话不是全部初始化 1 了，因为如果有障碍的话当前列和后边的列就都走不过了，初始化为 0
        for (; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[i] = 0;
                break;
            } else {
                dp[i] = 1;
            }
        }
        //障碍后边的列全部初始化为 0
        for (; i < n; i++) {
            dp[i] = 0;
        }
        for (i = 1; i < m; i++) {
            //这里改为从 0 列开始了，因为 62 题中从 1 开始是因为第 0 列永远是 1 不需要更新
            //这里的话，如果第 0 列是障碍的话，需要更新为 0
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else {
                    //由于从第 0 列开始了，更新公式有 j - 1，所以 j = 0 的时候不更新
                    if (j != 0)
                        dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[(n - 1)];
    }
}
