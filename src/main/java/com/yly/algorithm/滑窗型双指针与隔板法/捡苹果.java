package com.yly.algorithm.滑窗型双指针与隔板法;

public class 捡苹果 {

    /**
     * 通过隔板法转换成滑动窗口。
     * 复杂 ---> 简单
     */
    public int pickApples(int[] A, int K, int L) {
        int n = A.length;
        int maxApples = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int leftMaxL = findMax(A, L, 0, i);
            int rightMaxK = findMax(A, K, i, n);

            int leftMaxK = findMax(A, K, 0, i);
            int rightMaxL = findMax(A, L, i, n);

            if (leftMaxL != -1 && rightMaxK != -1) {
                maxApples = Math.max(leftMaxL + rightMaxK, maxApples);
            }
            if (leftMaxK != -1 && rightMaxL != -1) {
                maxApples = Math.max(leftMaxK + rightMaxL, maxApples);
            }
        }
        if (maxApples == Integer.MIN_VALUE) {
            return -1;
        }
        return maxApples;
    }


    // 滑动窗口
    int findMax(int[] A, int k, int start, int end) {
        if (k > end - start) {
            return -1;
        }
        int apples = 0, maxApples = 0;
        for (int i = start; i < start + k; i++) {
            apples += A[i];
        }
        maxApples = apples;

        int left = start, right = start + k;
        while (right < end) {
            apples -= A[left];
            apples += A[right];
            maxApples = Math.max(maxApples, apples);
            left++;
            right++;
        }
        return maxApples;
    }
}
