package com.yly.algorithm.前缀和.求和等于k的最短的子数组;

import java.util.HashMap;

public class 求和等于k的最短的子数组 {

    // 时间复杂度O(n)
    public int subarraySumEqualsKII(int[] nums, int k) {
        int[] prefixSum = getPrefixSum(nums);

        int answer = Integer.MAX_VALUE;

        // 变量命名小技巧
        // hashmap / dict 用 key2value  的方式命名，代表了 key 是 sum, value 是 index
        HashMap<Integer, Integer> sum2index = new HashMap<> ();
        sum2index.put(0, 0);
        for (int end = 0; end < nums.length; end++) {
            if (sum2index.containsKey(prefixSum[end + 1] - k)) {
                int len = end + 1 - sum2index.get(prefixSum[end + 1] - k);
                answer = Math.min(answer, len);
            }
            sum2index.put(prefixSum[end + 1], end + 1);
        }
        return answer;
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
        拓展：求和= k的最长的子数组
        math.min--->max
        if(!sum2index.get(prefixSum[end + 1])){     //不存在才可以进行赋值，存在不能进行覆盖
         sum2index.put(prefixSum[end + 1], end + 1);
        }
     */
}
