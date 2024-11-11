package com.yly.algorithm.链表.分割链表;

import com.yly.algorithm.链表.ListNode;

public class 分割链表 {
    public ListNode partition(ListNode head, int x) {
        //小于分区点的链表
        ListNode min_head = new ListNode(0);
        ListNode min = min_head;
        //大于等于分区点的链表
        ListNode max_head = new ListNode(0);
        ListNode max = max_head;

        //遍历整个链表
        while (head != null) {
            if (head.val < x) {
                min.next = head;
                min = min.next;
            } else {
                max.next = head;
                max = max.next;
            }

            head = head.next;
        }
        max.next = null;  //这步不要忘记，不然链表就出现环了
        //两个链表接起来
        min.next = max_head.next;

        return min_head.next;
    }
}
