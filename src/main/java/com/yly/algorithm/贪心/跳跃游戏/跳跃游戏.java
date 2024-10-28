package com.yly.algorithm.贪心.跳跃游戏;

public class 跳跃游戏 {
    public boolean canJump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //当前更新超过了边界，那么意味着出现了 0 ，直接返回 false
            if (end < i) {
                return false;
            }
            //找能跳的最远的 
            maxPosition = Math.max(maxPosition, nums[i] + i);

//	        if( i == end){ //遇到边界，就更新边界，并且步数加一,每次都更新边界也可以
//	            end = maxPosition; 
//	        }
            end = maxPosition;
        }
        //最远的距离是否到答末尾
        return maxPosition >= nums.length - 1;
    }
}




 