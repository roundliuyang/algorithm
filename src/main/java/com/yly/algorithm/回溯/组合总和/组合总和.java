package com.yly.algorithm.回溯.组合总和;

import java.util.ArrayList;
import java.util.List;

public class 组合总和 {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i);
                //找到了一个解或者 remain < 0 了，将当前数字移除，然后继续尝试
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}

 