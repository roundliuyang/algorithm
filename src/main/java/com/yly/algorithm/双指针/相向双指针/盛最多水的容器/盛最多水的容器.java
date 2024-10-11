package com.yly.algorithm.双指针.相向双指针.盛最多水的容器;

public class 盛最多水的容器 {
    public int maxArea(int[] height) {
        int maxarea = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
}
