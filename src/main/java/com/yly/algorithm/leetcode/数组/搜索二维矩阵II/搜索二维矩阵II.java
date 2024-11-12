package com.yly.algorithm.leetcode.数组.搜索二维矩阵II;

public class 搜索二维矩阵II {
    public boolean Find(int target, int[][] array) {
        int row = array.length - 1;
        int cow = array[0].length - 1;
        int i = 0;
        int j = cow;
        while (i <= row && j >= 0) {
            if (array[i][j] > target) {
                j--;
            } else if (array[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
}
