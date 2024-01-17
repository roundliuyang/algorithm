package com.yly.algorithm.二分法;

/**
 * 给定一个整数数组(size为n)，其具有以下特点：
 * 相邻位置的数字是不同的
 * A[0] < A[1] 并且 A[n - 2] > A[n - 1]
 * 假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，返回数组中任意一个峰值的位置。
 * <p>
 * 数组保证至少存在一个峰
 * 如果数组存在多个峰，返回其中任意一个就行
 * 数组至少包含 3 个数
 * <p>
 * 输入：
 * A = [1, 2, 1, 3, 4, 5, 7, 6]
 * 输出：
 * 1
 * 解释：
 * 返回任意一个峰顶元素的下标，6也同样正确。
 */
public class 寻找峰值 {

    /**
     * 上升区间的右侧必有峰值
     * 下降区间的左侧必有峰值
     * 谷底两侧必有峰值
     */
    public int findPeak(int[] a) {
        int start = 1;
        int end = a.length - 2;
        // 使用 start < end 有可能会造成死循环
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (a[mid] <= a[mid - 1]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (a[start] < a[end]) {
            return end;
        }
        return start;

    }
}
