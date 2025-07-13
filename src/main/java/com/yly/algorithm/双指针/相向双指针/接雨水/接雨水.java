package com.yly.algorithm.双指针.相向双指针.接雨水;


public class 接雨水 {
    int trap(int[] height) {
        if (height.length == 0){
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int ans = 0;

        int leftMax = height[0];
        int rightMax = height[right];

        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // ans += min(l_max, r_max) - height[i]
            if (leftMax < rightMax) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }
}

 