package com.yly.algorithm.位运算.只出现一次的数字;

public class 只出现一次的数字 {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
