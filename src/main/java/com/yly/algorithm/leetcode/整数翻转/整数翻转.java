package com.yly.algorithm.leetcode.整数翻转;

/**
 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 假设环境不允许存储 64 位整数（有符号或无符号）。
 
 示例 1：
 输入：x = 123
 输出：321
 
 示例 2：
 输入：x = -123
 输出：-321
 
 示例 3：
 输入：x = 120
 输出：21
 
 示例 4：
 输入：x = 0
 输出：0
 */
public class 整数翻转 {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
