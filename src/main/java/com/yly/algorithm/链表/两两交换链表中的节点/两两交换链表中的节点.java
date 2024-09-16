package com.yly.algorithm.链表.两两交换链表中的节点;

import com.yly.algorithm.链表.ListNode;


public class 两两交换链表中的节点 {
    public ListNode swapPairs(ListNode head) {
        if ((head == null) || (head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
}



 