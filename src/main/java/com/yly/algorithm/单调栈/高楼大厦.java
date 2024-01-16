package com.yly.algorithm.单调栈;


import java.util.Arrays;
import java.util.Stack;

/**
 * •给出一个长度为 n 的数组 arr，代表一排楼房的高度
 * • 求解在每一个楼的位置，向前向后看能看到多少座楼房。
 * • 如果前面楼房的高度大于等于后面楼房的高度，那么后面的楼房会被挡住。
 * • 在某一座楼的位置时，当前这座楼不会遮挡任何楼并且一定能被看到。
 * <p>
 * • 输入：[5,3,8,3,2,5]
 * • 输出：[3,3,5,4,4,4]
 */
public class 高楼大厦 {

    /**
     * 解法一：暴力法
     * 枚举每个下标，分别向左向右计算能看到多少个楼
     * 需要记录当前看到的最高楼 highest
     *
     * @param arr
     * @return
     */
    public int[] tallBuilding(int[] arr) {
        int[] results = new int[arr.length];
        // 一定能看到当前位置的楼
        Arrays.fill(results, 1);
        for (int i = 0; i < arr.length; i++) {
            // 右边能看多少楼
            countBuildings(arr, results, i, i + 1, arr.length, 1);
            // 左边能看多少楼
            countBuildings(arr, results, i, i - 1, -1, -1);
        }
        return results;
    }

    private void countBuildings(int[] arr,
                                int[] results,
                                int index,
                                int start,
                                int end,
                                int delta) {
        int highest = Integer.MIN_VALUE;
        int canBeSeen = 0;
        for (int i = start; i != end; i += delta) {
            if (highest < arr[i]) {
                highest = arr[i];
                canBeSeen++;
            }
        }
        results[index] += canBeSeen;
    }


    /**
     * 解法二：单调栈
     * 从头到尾，从尾到头进行两次单调栈
     * 分别站在某个位置上向左，向右能看见多少楼房
     * 入栈前的栈的大小就是能看见的房子数量
     */
    public int[] tallBuilding2(int[] arr) {
        int[] results = new int[arr.length];
        // 一定能看到当前位置的楼
        Arrays.fill(results, 1);

        // 右边能看多少楼
        countBuildings2(arr, results, 0, arr.length, 1);
        // 左边能看多少楼
        countBuildings2(arr, results, arr.length - 1, -1, -1);

        return results;
    }

    private void countBuildings2(int[] arr,
                                 int[] results,
                                 int start,
                                 int end,
                                 int delta) {
        Stack<Integer> stack = new Stack<>();
        for (int i = start; i != end; i += delta) {
            results[i] += stack.size();
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            stack.push(i);
        }
    }
}
