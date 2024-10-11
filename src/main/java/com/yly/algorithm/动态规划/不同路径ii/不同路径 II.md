# 不同路径 II



## 题目 

给定一个 `m x n` 的整数数组 `grid`。一个机器人初始位于 **左上角**（即 `grid[0][0]`）。机器人尝试移动到 **右下角**（即 `grid[m - 1][n - 1]`）。机器人每次只能向下或者向右移动一步。

网格中的障碍物和空位置分别用 `1` 和 `0` 来表示。机器人的移动路径中不能包含 **任何** 有障碍物的方格。

返回机器人能够到达右下角的不同路径数量。

测试用例保证答案小于等于 `2 * 109`。

 

**示例 1：**

![img](不同路径 II.assets/robot1.jpg)

```
输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
```

**示例 2：**

![img](不同路径 II.assets/robot2.jpg)

```
输入：obstacleGrid = [[0,1],[0,0]]
输出：1
```

 

**提示：**

- `m == obstacleGrid.length`
- `n == obstacleGrid[i].length`
- `1 <= m, n <= 100`
- `obstacleGrid[i][j]` 为 `0` 或 `1`



## 解题思路

题目关键词：路径

通常是在一维数组、二维数组（矩阵）中求最优路径、路径数等 一般使用坐标型动态规划。

dp[坐标] = 从起点到当前坐标的最优值、路径数、可行性。



## 代码

```java
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
```

