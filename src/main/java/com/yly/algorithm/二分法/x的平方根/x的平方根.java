package com.yly.algorithm.二分法.x的平方根;

public class x的平方根 {
    public int mySqrt(int x) {
        int L = 1, R = x;
        int ans = 0; //保存最后的解
        while (L <= R) {
            int mid = L + (R - L) / 2;
            long square = (long) mid * mid;
            if (square == x) {
                return mid;
            } else if (square < x) {
                ans = mid; //存起来以便返回
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return ans;
    }
}
