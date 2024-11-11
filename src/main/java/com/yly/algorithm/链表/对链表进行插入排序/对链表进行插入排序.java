package com.yly.algorithm.链表.对链表进行插入排序;

import com.yly.algorithm.链表.ListNode;


/**
 * 和数组的插入排序思想一模一样
 * 一个是链表一个是数组
 */
public class 对链表进行插入排序 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(0);//建立一个新的节点
        ListNode pointer = head;//用pointer指针表明当前剩余的待排序的链表。
        while (pointer != null) {
            ListNode temp = pointer;//取待排序链表的第一个进行排序
            pointer = pointer.next;//更新剩余未排序的链表，进行下一次的循环
            ListNode p = newHead;

            while (p.next != null && p.next.val < temp.val) {
                p = p.next;
            }
            temp.next = p.next;
            p.next = temp;

        }
        return newHead.next;
    }
}
