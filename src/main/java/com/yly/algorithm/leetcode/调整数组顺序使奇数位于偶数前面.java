package com.yly.algorithm.leetcode;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * 解法二
 * 插入排序 原地算法  不浪费新的空间
 */

class Solution2 {
    public void reOrderArray(int[] array) {
        int len = array.length;
        if (len <= 1) return;
        int last = -1;
        for (int i = 0; i < len; i++) {

            if (array[i] % 2 != 0) {
                int newOdd = array[i];
                for (int j = i - 1; j > last; j--)
                    array[j + 1] = array[j];
                array[last + 1] = newOdd;
                last++;
            }
        }

    }
}

public class 调整数组顺序使奇数位于偶数前面 {
    public void reOrderArray(int[] array) {
        int[] arr = new int[array.length];
        int l = 0;
        int r = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                arr[l] = array[i];
                l++;
            }

        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] % 2 == 0) {
                arr[r] = array[i];
                r--;
            }
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = arr[i];//数组复制过去
        }
    }
}


// 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
class Solution3 {
    public int[] exchange(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (array[left] % 2 == 1) {
                left++;
            } else if (array[right] % 2 == 0) {
                right--;
            } else {
                int temp;
                temp = array[left];
                array[left++] = array[right];
                array[right--] = temp;
            }

        }
        return array;
    }
}


