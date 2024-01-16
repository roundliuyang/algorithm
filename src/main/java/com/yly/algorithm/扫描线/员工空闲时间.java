package com.yly.algorithm.扫描线;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 我们得到一个员工的schedule列表,代表每个员工工作时间。
 * 每个员工有一个不重合时段的列表 Intervals,这些时段按序排列。
 * 返回一个所有员工共有的空闲时段的列表，并按序排列。
 * 我们的Intervals是一个一维数组，其中每两个数表示一个区间，即[1,2,8,10]表示这个员工的工作时间是[1,2]和[8,10]。
 * 并且,我们的答案不会包括像[5,5]这样的,因为它们的长度是0。
 * <p>
 * 样例 1:
 * 输入：schedule = [[1,2,5,6],[1,3],[4,10]]
 * 输出：[(3,4)]
 * 解释：共有三个员工,并且所有员工共有的空闲时段是[-inf, 1], [3, 4], [10, inf]。去除掉包含inf的答案。
 */
public class 员工空闲时间 {

    /**
     * 思路整理
     * 排序所有时间点，每次拿出一个时间点 time
     * 如果 time 是一个右区间，下一个时间点是一个左区间
     * 并且扫描线当前没有扫到区间
     * 说明这个区间是一个没有员工工作的时间段，将其加入答案
     */
    public List<Interval> employeeFreeTime(int[][] schedule) {

        Comparator<Boundary> comparator = new Comparator<Boundary>() {
            @Override
            public int compare(Boundary a, Boundary b) {
                if (a.num == b.num) {
                    return a.type - b.type;
                }
                return a.num - b.num;
            }
        };
        PriorityQueue<Boundary> heap = new PriorityQueue<>(comparator);
        for (int[] employee : schedule) {
            for (int i = 0; i < employee.length; i += 2) {
                heap.add(new Boundary(employee[i], -1));
                heap.add(new Boundary(employee[i + 1], 1));
            }
        }

        LinkedList<Interval> result = new LinkedList<>();
        int count = 0;
        while (heap.size() > 1) {
            Boundary left = heap.poll();
            Boundary right = heap.peek();
            count += left.type;
            if (left.type == 1 && right.type == -1 && count == 0) {
                result.add(new Interval(left.num, right.num));
            }
        }
        return result;
    }
}
