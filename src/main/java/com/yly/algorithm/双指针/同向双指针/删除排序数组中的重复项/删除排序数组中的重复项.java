package com.yly.algorithm.双指针.同向双指针.删除排序数组中的重复项;

/**
 * 利用快慢指针
 * 与移除元素一模一样
 * 给定一个已排序的数组，使用就地算法将重复的数字移除，使数组中的每个元素只出现一次，返回新数组的长度。
 */
public class 删除排序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[right] != nums[left]) {
                left++;
                nums[left] = nums[right];
            }
        }
        return left + 1;
    }
}

 