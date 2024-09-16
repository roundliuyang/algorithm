package com.yly.algorithm.链表.反转链表;


import com.yly.algorithm.链表.ListNode;

public class 反转链表 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextTemp;
        }
        return pre;
    }
}
