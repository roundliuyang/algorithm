package com.yly.algorithm.双指针.相向双指针.和为S的两个数字;

import java.util.ArrayList;
import java.util.List;


/**
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * a+b等于定值
 */
public class 和为S的两个数字 {
    public List<Integer> FindNumbersWithSum(int[] array, int sum) {
        int i = 0;
        int j = array.length - 1;
        List<Integer> res = new ArrayList();
        while (i < j) {
            int s = array[i] + array[j];
            if (s < sum) {
                i++;
            } else if (s > sum) {
                j--;
            } else {
                res.add(array[i]);
                res.add(array[j]);
                return res;
            }
        }
        return res;
    }
}
