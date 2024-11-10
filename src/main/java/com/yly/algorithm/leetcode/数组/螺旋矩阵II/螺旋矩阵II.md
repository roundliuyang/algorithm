# 螺旋矩阵II



## 题目描述

给你一个正整数 `n` ，生成一个包含 `1` 到 `n2` 所有元素，且元素按顺时针顺序螺旋排列的 `n x n` 正方形矩阵 `matrix` 。

 

**示例 1：**

![img](螺旋矩阵II.assets/spiraln.jpg)

```
输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]
```

**示例 2：**

```
输入：n = 1
输出：[[1]]
```

 

**提示：**

- `1 <= n <= 20`

## 解题思路



## 代码

```java
public class 螺旋矩阵II {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n < 1)
            return res;
        int index = 1;
        int rowStart = 0, rowEnd = n - 1;
        int colStart = 0, colEnd = n - 1;
        while (index <= n * n) {
            for (int i = colStart; i <= colEnd; i++) {
                res[rowStart][i] = index++;
            }
            for (int i = rowStart + 1; i <= rowEnd; i++) {
                res[i][colEnd] = index++;
            }
            for (int i = colEnd - 1; i >= colStart; i--) {
                res[rowEnd][i] = index++;
            }
            for (int i = rowEnd - 1; i > rowStart; i--) {
                res[i][colStart] = index++;
            }
            rowStart += 1;
            rowEnd -= 1;
            colStart += 1;
            colEnd -= 1;
        }
        return res;
    }
}
```

