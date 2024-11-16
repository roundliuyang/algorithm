package com.yly.algorithm.位运算.只出现一次的数字II;

import java.util.HashMap;

/**
 * 位运算 todo
 */
public class 只出现一次的数字II {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int num : nums) {
            hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);
        }
        for (int k : hashmap.keySet())
            if (hashmap.get(k) == 1) {
                return k;
            }
        return -1;
    }


    /**
     * 现在有一个整数类型的数组，数组中素只有一个元素只出现一次，其余的元素都出现两次。
     * 位运算 0^=A == A
     */
    public int singleNumber2(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
