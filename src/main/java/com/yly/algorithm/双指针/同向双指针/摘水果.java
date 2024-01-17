package com.yly.algorithm.双指针.同向双指针;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 小红去果园采水果。有2个篮子，可以装无数个水果，但是只能装一种水果。从任意位置的树开始，往右采。遇到2种情况退出，
 * 1. 遇到第三种水果，没有篮子可以放了，2. 到头了。返回可以采摘的最多的水果个数。水果数组用arr表示。
 * <p>
 * 样例 1:
 * 输入：[1,2,1,3,4,3,5,1,2]
 * 输出：3
 * 解释：
 * 选择[1, 2, 1] 或 [3, 4, 3]。 长度是3。
 */
public class 摘水果 {
    public static void main(String[] args) {
        摘水果 cla = new 摘水果();
        int[] arr = {1, 2, 1, 3, 4, 3, 5, 1, 2};
        System.out.println(cla.pickFruits2(arr));
    }

    /**
     * 枚举法
     * • 时间复杂度：O(n ^ 3)
     * • 空间复杂度：O(n)
     */
    public int pickFruits(int[] arr) {
        int maxLength = 0;
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                HashSet<Integer> set = new HashSet<>();
                for (int i = start; i <= end; i++) {
                    set.add(arr[i]);
                }
                if (set.size() <= 2) {
                    maxLength = Math.max(maxLength, end - start + 1);
                }
            }
        }
        return maxLength;
    }


    /**
     * 同向双指针
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int pickFruits2(int[] arr) {
        int j = 0;
        int differentNumbers = 0;
        int maxLength = 0;
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            while (j < arr.length && differentNumbers < 3) {
                count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);
                if (count.get(arr[j]) == 1) {
                    differentNumbers++;
                }
                j++;
                if (differentNumbers <= 2) {
                    maxLength = Math.max(maxLength, j - i);
                }
            }
            count.put(arr[i], count.getOrDefault(arr[i], 0) - 1);
            if (count.get(arr[i]) == 0) {
                differentNumbers--;
            }
        }
        return maxLength;
    }

}
