package com.yly.algorithm.leetcode.数组.螺旋矩阵;

import java.util.ArrayList;
import java.util.List;

public class 螺旋矩阵 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0, right = columns - 1;
        int top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                order.add(matrix[top][i]);
            }
            for (int i = top + 1; i <= bottom; i++) {
                order.add(matrix[i][right]);
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i >= left; i--) {
                    order.add(matrix[bottom][i]);
                }
                for (int i = bottom - 1; i > top; i--) {
                    order.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
