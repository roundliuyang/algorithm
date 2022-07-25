package com.yly.algorithm.滑窗型双指针与隔板法;

public class 爱生气的书店老板 {
    /*
        题目描述：见笔记
     */

    public int maxSatisfied(int[] customers, int[] grumpy, int x) {
        int n = customers.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i < x) {
                sum += customers[i];
            } else {
                sum += (1 - grumpy[i]) * customers[i];
            }
        }
        int result = sum;
        int left = 0;
        int right = x;
        while (right < n) {
            if (grumpy[right] == 1) {
                sum += customers[right];
            }
            if (grumpy[left] == 1) {
                sum -= customers[left];
            }
            result = Math.max(result, sum);
            left++;
            right++;
        }
        return result;
    }
}
