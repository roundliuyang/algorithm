package com.yly.algorithm.二分法.寻找峰值;

public class 寻找峰值 {
    
    public int findPeak(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            
            // 如果中间元素小于右侧元素，峰值在右侧
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                // 否则峰值在左侧
                right = mid;
            }
        }

        // 当 left == right 时，即找到了峰值
        return left;
    }
}
