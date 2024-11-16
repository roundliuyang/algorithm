package com.yly.algorithm.动态规划.最小路径和;


/**
 * 这里我们直接用 grid 覆盖存，不去 new 一个 n 的空间了。
 */
public class 最小路径和 {

    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 定义一个 dp 数组，dp[i][j] 表示到达 (i, j) 点的最小路径和
        int[][] dp = new int[m][n];

        // 初始化起点
        dp[0][0] = grid[0][0];

        // 初始化第一行
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // 初始化第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // 填充 dp 表
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 状态转移方程
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }

        // 返回右下角的值，即为最小路径和
        return dp[m - 1][n - 1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //由于第一行和第一列不能用我们的递推式，所以单独更新
        //更新第一行的权值
        for (int i = 1; i < n; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }
        //更新第一列的权值
        for (int i = 1; i < m; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        //利用递推式更新其它的
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];

            }
        }
        return grid[m - 1][n - 1];
    }
}

 