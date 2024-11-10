package com.yly.algorithm.leetcode.数组.螺旋矩阵II;

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
