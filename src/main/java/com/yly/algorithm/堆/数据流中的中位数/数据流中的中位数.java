package com.yly.algorithm.堆.数据流中的中位数;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 大顶堆与小顶堆
 */
public class 数据流中的中位数 {
    Queue<Integer> A, B;

    public 数据流中的中位数() {
        A = new PriorityQueue<>(); // 小顶堆，保存较大的一半
        B = new PriorityQueue<>((x, y) -> (y - x)); // 大顶堆，保存较小的一半
    }

    public void addNum(int num) {
        if (A.size() != B.size()) {
            A.add(num);
            B.add(A.poll());
        } else {
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian() {
        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
    }
}
