package com.yly.algorithm.链表.环形链表;

import com.yly.algorithm.链表.ListNode;

import java.util.HashSet;
import java.util.Set;

public class 环形链表 {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<ListNode>();
        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;

    }
}
