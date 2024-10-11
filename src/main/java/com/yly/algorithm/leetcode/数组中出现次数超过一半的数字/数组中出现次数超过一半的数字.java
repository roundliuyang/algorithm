package com.yly.algorithm.leetcode.数组中出现次数超过一半的数字;

import java.util.HashMap;
import java.util.Map;


public class 数组中出现次数超过一半的数字 {
    /**
     * 哈希表统计法
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                //如果已经存在key，将值+1 
                int temp = map.get(nums[i]);
                map.put(nums[i], temp + 1);
            } else
                map.put(nums[i], 1);
        }
        //遍历hashmap，找出值大于数组一般的key，返回
        for (Integer i : map.keySet()) {
            if (map.get(i) * 2 > nums.length)
                return i;
        }
        return 0;
    }

    /**
     * 摩尔投票法
     */
    public int majorityElement2(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }
}
