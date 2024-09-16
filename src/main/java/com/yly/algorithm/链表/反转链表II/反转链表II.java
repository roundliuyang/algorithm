package com.yly.algorithm.链表.反转链表II;

import com.yly.algorithm.链表.ListNode;


public class 反转链表II {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preLeft = dummy;

        // Move preLeft to the node before left
        for (int i = 0; i < left - 1; i++) {
            preLeft = preLeft.next;
        }

        ListNode pre = preLeft.next;
        ListNode curr = pre.next;

        // Reverse the nodes between left and right
        for (int i = 0; i < right - left; i++) {
            ListNode nextTemp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextTemp;
        }

        // Connect the reversed part with the rest of the list
        preLeft.next.next = curr;
        preLeft.next = pre;

        return dummy.next;
    }
}
