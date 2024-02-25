package com.yly.algorithm.链表;

/**
 * leetcode 206
 * 反转一个单链表。
 */
public class 反转链表 {
    /**
     * a->b->c 变成 a<-b<-c  
     * 解法（套路）a->b->c  引入一个pre，进行反转
     *            a (curr)───┐   b(nextTemp)
     *             ┌─────────┘  
     *             ▼
     *             pre
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        
        while(curr.next != null){
            ListNode nextTemp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextTemp;
        }
        return pre;
    }
}
