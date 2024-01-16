package com.yly.algorithm.扫描线;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定一系列的会议时间间隔intervals，包括起始和结束时间[[s1,e1],[s2,e2],...] (si < ei)，找到所需的最小的会议室数量。
 * (0,8),(8,10)在8这一时刻不冲突
 * <p>
 * 样例1
 * 输入: intervals = [(0,30),(5,10),(15,20)]
 * 输出: 2
 * 解释:
 * 需要两个会议室
 * 会议室1:(0,30)
 * 会议室2:(5,10),(15,20)
 */
public class 会议室II {
    public int minMeetingRooms(List<Interval> intervals) {
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
        for (Interval interval : intervals) {
            heap.add(new Boundary(interval.start, 1));
            heap.add(new Boundary(interval.end, -1));
        }
        int meetings = 0;
        int result = 0;
        while (!heap.isEmpty()) {
            meetings += heap.poll().type;
            result = Math.max(meetings, result);
        }
        return result;

    }
}
