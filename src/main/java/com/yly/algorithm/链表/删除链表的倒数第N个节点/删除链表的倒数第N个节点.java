package com.yly.algorithm.链表.删除链表的倒数第N个节点;

import com.yly.algorithm.链表.ListNode;

public class 删除链表的倒数第N个节点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len;
        ListNode node = head;
        ListNode m = head;
        for (len = 0; m != null; len++) {
            m = m.next;
        }
        if (n < len) {
            for (int i = 1; i < len - n; i++) {
                node = node.next;
            }
            ListNode next = node.next;
            node.next = node.next.next;
            next = null;
            return head;
        } else {
            return head.next;
        }
    }
}
