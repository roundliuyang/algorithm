package com.yly.algorithm.leetcode.数组.旋转图像;

/**
 * 可以先转置，然后把每列对称交换交换一下
 */
public class 旋转图像 {
    public void rotate(int[][] matrix) {
        //以对角线为轴交换
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    continue;
                }
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //交换列
        for (int i = 0, j = matrix.length - 1; i < matrix.length / 2; i++, j--) {
            for (int k = 0; k < matrix.length; k++) {
                int temp = matrix[k][i];
                matrix[k][i] = matrix[k][j];
                matrix[k][j] = temp;
            }
        }

    }
}
