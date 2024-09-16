package com.yly.algorithm.链表.k个链表一组翻转;

import com.yly.algorithm.链表.ListNode;

public class k个链表一组翻转 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;
        ListNode point = head;
        //找到子链表的尾部
        int i = k;
        while (i - 1 > 0) {
            point = point.next;
            if (point == null) {
                return head;
            }
            i--;
        }
        ListNode temp = point.next;
        //将子链表断开
        point.next = null;

        //倒置子链表，并接收新的头结点
        ListNode new_head = reverse(head);

        //head 其实是倒置链表的尾部，然后我们将后边的倒置结果接过来就可以了
        //temp 是链表断开后的头指针
        head.next = reverseKGroup(temp, k);
        return new_head;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
