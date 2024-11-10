package com.yly.algorithm.回溯.组合;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个 for 循环，添加，递归，删除，很经典的回溯框架了。
 */
public class 组合 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        getAns(1, n, k, new ArrayList<Integer>(), ans);
        return ans;
    }

    private void getAns(int start, int n, int k, ArrayList<Integer> temp, List<List<Integer>> ans) {
        //如果 temp 里的数字够了 k 个，就把它加入到结果中
        if (temp.size() == k) {
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        //从 start 到 n
        for (int i = start; i <= n; i++) {
            //将当前数字加入 temp
            temp.add(i);
            //进入递归
            getAns(i + 1, n, k, temp, ans);
            //将当前数字删除，进入下次 for 循环
            temp.remove(temp.size() - 1);
        }
    }
}
