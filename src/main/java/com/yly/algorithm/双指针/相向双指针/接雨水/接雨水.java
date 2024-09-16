package com.yly.algorithm.双指针.相向双指针.接雨水;


public class 接雨水 {
    int trap(int[] height) {
        if (height.length == 0) return 0;
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int ans = 0;

        int l_max = height[0];
        int r_max = height[n - 1];

        while (left <= right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // ans += min(l_max, r_max) - height[i]
            if (l_max < r_max) {
                ans += l_max - height[left];
                left++;
            } else {
                ans += r_max - height[right];
                right--;
            }
        }
        return ans;
    }
}

 