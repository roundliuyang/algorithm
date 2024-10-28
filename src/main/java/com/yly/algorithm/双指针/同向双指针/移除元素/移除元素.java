package com.yly.algorithm.双指针.同向双指针.移除元素;

/**
 * 利用快慢指针
 * 给定一个数组和一个值，使用就地算法将数组中所有等于这个值的元素删除，并返回新数组的长度
 */
public class 移除元素 {
    public int removeElement(int[] nums, int val) {
        int fast = 0;
        int slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }
}

 