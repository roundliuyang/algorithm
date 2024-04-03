package com.yly.algorithm.贪心;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
    给出若干闭合区间，合并所有重叠的部分。
    输入：[(1,3), (2,6), (8,10), (15,18)]
    输出：[(1,6), (8,10), (15,18)]

    解法一：贪心
    将intervals数组排序，左边界越小越优先前一个区间的右边界在后一个区间的左边界之后，则合并两个区间
    前一个区间的右边界在后一个区间的左边界之后 == 两区间相交
 */
public class 合并所有重叠的区间 {
    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len < 2) {
            return intervals;
        }
        // 按照起点排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        // 也可以使用 Stack，因为我们只关心结果集的最后一个区间
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < len; i++) {
            int[] curInterval = intervals[i];

            // 每次新遍历到的列表与当前结果集中的最后一个区间的末尾端点进行比较
            int[] peek = res.get(res.size() - 1);

            if (curInterval[0] > peek[1]) {
                res.add(curInterval);
            } else {
                // 注意，这里应该取最大
                peek[1] = Math.max(curInterval[1], peek[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}




 