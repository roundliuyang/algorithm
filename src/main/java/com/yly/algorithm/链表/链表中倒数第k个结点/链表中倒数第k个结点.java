package com.yly.algorithm.链表.链表中倒数第k个结点;

import com.yly.algorithm.链表.ListNode;


/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class 链表中倒数第k个结点 {
    public ListNode FindKthToTail(ListNode head, int k) {
        // len 为 链表的长度
        int len = 0;
        ListNode node = head;
        ListNode tmp = head;
        for (; tmp != null; len++){
            tmp = tmp.next;
        }
        if (len == k){
            return head;
        } else if(k < len){
            for(int i = 0; i < len - k; i++){
                node = node.next;
            }
            return node;
        }else {
            return null;
        }
    }
}
