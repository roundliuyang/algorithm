package com.yly.algorithm.单调栈.滑动窗口的最大值;


import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class 滑动窗口的最大值 {


    public List<Integer> maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new LinkedList<>();
        }

        LinkedList<Integer> results = new LinkedList<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            // 单调栈递减
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);

            // 从 i = k - 1 开始计算答案
            if (i >= k - 1) {
                results.add(nums[deque.peekFirst()]);
            }

            // 第 i 个元素进入窗口
            // 相当于第 i-k+1 个元素应该离开窗口
            if (i - k + 1 == deque.peekFirst()) {
                deque.pollFirst();
            }
        }
        return results;
    }
}
