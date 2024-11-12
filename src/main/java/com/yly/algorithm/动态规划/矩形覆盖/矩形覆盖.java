package com.yly.algorithm.动态规划.矩形覆盖;

public class 矩形覆盖 {
    public int rectCover(int target) {
        if (target <= 2)
            return target;
        int dpi_2 = 1;
        int dpi_1 = 2;
        int res = 0;
        for (int i = 3; i <= target; i++) {
            res = dpi_1 + dpi_2;
            dpi_2 = dpi_1;
            dpi_1 = res;
        }
        return res;
    }
}
