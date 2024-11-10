package com.yly.algorithm.unknown;


/**
 *经典回溯法
 * 给你长度为n的序列A，算出多少三元组（Ai,Aj,Ak）,满足Ai小于等于Aj,Aj小于等于Ak
 */

//public class 回溯三元组 {
//
//    public static void dfs(int[] nums, boolean[] used) {
//        if (path.size() == 3) {
//            ret++;
//            return;
//        }
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i - 1] > nums[i] || used[i - 1]) {
//                continue;
//            }
//            path.add(nums[i - 1]);
//            used[i - 1] = true;
//            dfs(nums, used);
//            path.remove(path.size() - 1);
//            used[i - 1] = false;
//        }
//    }
//}