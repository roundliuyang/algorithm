package com.yly.algorithm.扫描线;

import java.util.*;

/*
    给出若干闭合区间，合并所有重叠的部分。
    输入：[(1,3), (2,6), (8,10), (15,18)]
    输出：[(1,6), (8,10), (15,18)]

    解法二：扫描线算法
    使用扫描线思想，将每个边界标记为左边界或右边界
    将所有的边界排序，扫描排序后的数组
    找到一个左边界，不断更新当前区间可能的右边界位置
    如果右边界不可能延伸，则终止当前区间的计算
 */
public class 合并所有重叠的区间 {

    public List<Interval> merge(List<Interval> intervals) {

        Comparator<Boundary> comparator = new Comparator<Boundary>() {
            public int compare(Boundary a, Boundary b) {
                if (a.num == b.num) {
                    return a.type - b.type;
                }
                return a.num - b.num;
            }
        };

        List<Boundary> boundaries = new LinkedList<>();
        for (Interval interval : intervals) {
            boundaries.add(new Boundary(interval.start, -1));
            boundaries.add(new Boundary(interval.end, 1));
        }
        Collections.sort(boundaries, comparator);
        List<Interval> result = new LinkedList<>();
        int isMatched = 0, left = 0, right = 0;
        for (Boundary boundary : boundaries) {
            if (isMatched == 0) {
                left = boundary.num;
            }
            isMatched += boundary.type;
            if (isMatched == 0) {
                right = boundary.num;
                result.add(new Interval(left, right));
            }
        }
        return result;
    }
}

class Boundary {
    int num, type;

    public Boundary(int num, int type) {
        this.num = num;
        this.type = type;
    }
}




class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}





 