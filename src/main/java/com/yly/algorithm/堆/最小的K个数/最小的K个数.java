package com.yly.algorithm.堆.最小的K个数;

import java.util.Arrays;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4
 * 大顶堆/小顶堆的解法 TODO TODO
 */
public class 最小的K个数 {
    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }
}
