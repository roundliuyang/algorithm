package com.yly.algorithm.leetcode.求1到n的和;

public class 求1到n的和 {
    int res = 0;

    public int sumNums(int n) {
        boolean x = n > 1 && sumNums(n - 1) > 0;
        res += n;
        return res;
    }
}
