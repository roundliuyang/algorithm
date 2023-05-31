package com.yly.algorithm.双指针.同向双指针;


/*
     LeetCode 209
    给定一个含有n个正整数的数组和一个正整数 s 。
    找出该数组中满足其和 ≥ s 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class 长度最小的子数组 {
    // 暴力解法(注意边界)  时间复杂度O(n^3),空间复杂度 O(1)
    public int minimumSize(int[] nums, int s) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        for (int start = 0; start < n; start++) {
            for (int end = start + 1; end <= n; end++) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                if (sum >= s) {
                    minLength = Math.min(minLength, end - start);
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -0;
        }
        return minLength;
    }

    // 暴力解法(注意边界，和上面边界有一些不同)
    public int minimumSize2(int[] nums, int s) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += nums[i];
                }
                if (sum >= s) {
                    minLength = Math.min(minLength, end - start + 1);
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;
    }

    /*
        怎样快速得到子数组的和(这样复杂度能从O(n^3)--->O(n^2))
        优化一：使用前缀和数组在O(1)的时间复杂度内计算子数组和
        如：nums   = [2,3,1,2,4,3]
        prefixSum = [0,2,5,6,8,12,15]
        index     = [0,1,2,3,4,5 ,6 ]
     */
    // 使用前缀和优化
    public int minimumSize3(int[] nums, int s) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        int[] prefixSum = getPrefixSum(nums);

        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                if (prefixSum[end + 1] - prefixSum[start] >= s) {
                    minLength = Math.min(minLength, end - start + 1);
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;
    }

    private int[] getPrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        return prefixSum;
    }


    /*
        注意此题必须是正整数才是xxoo的模式，才能用二分
        优化二：二分
        对于每个下标 i ，都让他作为子数组左边界
        使用二分法找出子数组最靠左的右边界
        使用前缀和求出子数组之和，与s比较并更新答案
        二分法的 XXOO 模型
        nums =      [2,3,1,2,4,3]
        prefixSum = [0,2,5,6,8,12,15]
        index  =    [0,1,2,3,4,5,6]
     */
    // 时空复杂度，枚举起点位置：O(n),二分终点位置：O(log n),总时间复杂度：O（n*log n）,总空间复杂度： O（n）

    public int minimumSize4(int[] nums, int s) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        int[] prefixSum = getPrefixSum2(nums);
        // start：子数组左端点
        for (int start = 0; start < n; start++) {
            int end = getEndOfSubarray(prefixSum, start, s);
            if (prefixSum[end + 1] - prefixSum[start] >= s) {
                minLength = Math.min(minLength, end - start + 1);
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;

    }

    private int getEndOfSubarray(int[] prefixSum, int start, int s) {
        int left = start;
        int right = prefixSum.length - 2; // 边界问题，由于prefix是从1开始的（prefixSum[i+1]）,所以，为了防止越界，必须是-2
        while (left + 1 < right) {        // 边界问题，left+1 <2,可以确保left 一定在right 左面
            int mid = left + (right - left) / 2;
            if (prefixSum[mid + 1] - prefixSum[start] >= s) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (prefixSum[left + 1] - prefixSum[start] >= s) {
            return left;
        }
        return right;

        /*

            假如nums=[1,1,1,1],start =0;prefixSum 值为  0,1,2,3,4
            则 s=3,此函数返回 2(nums 索引)
            则 s=0,此函数返回0---------->后续校验
            则 s=4,次函数返回3
            则 s=5,次函数返回3------后需校验
         */
    }

    private int[] getPrefixSum2(int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        return prefixSum;
    }


    /*
        方案四： 同向双指针
        同向双指针 （时间复杂度为 O(2 * n) = O(n)，空间复杂度 O(1) ）
        遍历每一个左指针 i,找到满足 sum(a[i],…,a[j]) >= s 的右指针 j,更新最短的子数组长度

        复杂度分析
        对于一个数字nums[i]，每个指针只会遍历一次,所以时间复杂度为 O(2 * n) = O(n),空间复杂度 O(1)
     */

    public int minimumSize5(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        int j= 0;
        for(int i =0; i < n;i++){
            while(j < n && sum < s){
                sum += nums[j];
                j++;
            }
            if(sum >= s){
                minLength = Math.min(minLength, j-i);
            }
            sum -= nums[i];
        }
        if(minLength == Integer.MAX_VALUE){
            return  0;
        }
        return  minLength;
    }


}
