package com.yly.algorithm.二分法.木材加工;

public class 木材加工 {

    public int woodCut(int[] L, int k) {
        int len = L.length;
        if (len == 0) {
            return 0;
        }

        int max = 0;  // 寻找最大值
        for (int i = 0; i < len; i++) {
            max = Math.max(max, L[i]);
        }
        int left = 0;
        int right = max;
        int mid;
        while (left + 1 < right) {
            mid = (left + (right - left) / 2);
            if (check(L, k, len, mid)) {     //如果还能分更长的，则往[mid,right]走
                left = mid;
            } else {                         //如果不能分更长的，则往[left,mid]走
                right = mid;
            }
        }
        if (check(L, k, len, right)) {
            return right;
        }
        return left;
    }

    private boolean check(int[] L, int k, int len, int mid) {
        int count = 0;
        //计算当前长度下能分成几段
        for (int i = 0; i < len; i++) {
            count += L[i] / mid;
        }
        //如果还能分更长的，返回true
        if (count >= k) {
            return true;
        }
        //如果不能分更长的，返回false
        return false;
    }
}
