package com.yly.algorithm.链表.链表中倒数第k个结点;

import com.yly.algorithm.链表.ListNode;


/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class 链表中倒数第k个结点 {
    public ListNode FindKthToTail(ListNode head, int k) {
        int s;
        ListNode node = head;
        ListNode m = head;
        for (s = 0; m != null; s++){
            m = m.next;
        }
        if (k < s) {
            for (int i = 0; i < s - k; i++) {
                node = node.next;
            }
            return node;
        } else if (k == s) {
            return head;
        } else {
            return null;
        }
    }
}
