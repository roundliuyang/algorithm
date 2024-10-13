package com.yly.algorithm.链表.两个链表的第一个公共结点;

import com.yly.algorithm.链表.ListNode;

/**
 * 输入两个链表，找出它们的第一个公共结点。
 * 我们使用两个指针 node1，node2 分别指向两个链表 headA，headB 的头结点，然后同时分别逐结点遍历，当 node1 到达
 * 链表 headA 的末尾时，重新定位到链表 headB 的头结点；当 node2 到达链表 headB 的末尾时，重新定位到链表 headA 的头结点。
 * 这样，当它们相遇时，所指向的结点就是第一个公共结点。
 */
public class 两个链表的第一个公共结点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //思路：双指针法。
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = (pA == null ? headB : pA.next);
            pB = (pB == null ? headA : pB.next);
        }
        return pA;
    }
}




 