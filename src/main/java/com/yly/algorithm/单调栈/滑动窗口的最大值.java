package com.yly.algorithm.单调栈;


import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出一个可能包含重复的整数数组，和一个大小为 k 的滑动窗口, 从左到右在数组中滑动这个窗口，找到数组中每个窗口内的最大值。
 * 输入:
 * [1,2,7,7,8]
 * 3
 * 输出:
 * [7,7,8]
 */
public class 滑动窗口的最大值 {


    /**
     * 暴力法
     * 最简单直接的方法是遍历每个滑动窗口，找到每个窗口的最大值。一共有 N - k + 1 个滑动窗口，每个有 k 个元素，
     * <p>
     * 复杂度分析复杂度分析
     * 窗口的长度给定，只需要枚举起点：O(n)
     * 打擂台计算窗口内的最大值：O(k)
     * 总时间复杂度：O(n * k)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            output[i] = max;
        }
        return output;
    }


    /**
     * 使用双向队列实现单调队列
     * 元素进入滑动窗口时，将队尾看成单调栈栈顶
     * 在元素离开滑动窗口时，从双向队列中弹出队首
     */
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
