package com.yly.algorithm.链表;
/**
 * 和k个链表一组翻转一模一样
 * 将给定的链表中每两个相邻的节点交换一次，返回链表的头指针
 */
public class 两两交换链表中的节点{
	public ListNode swapPairs(ListNode head) {
	    if ((head == null)||(head.next == null))
	        return head;
	    ListNode n = head.next;
	    head.next = swapPairs(head.next.next);
	    n.next = head;
	    return n;
	}
}
//k个链表一组翻转    k=2的情况
/**
 class Solution {
    public ListNode swapPairs(ListNode head) {

    
        int k=2;
	    if (head == null)
	        return null;
	    ListNode point = head;
	    //找到子链表的尾部
	    int i = k;
	    while(i - 1 >0){
	        point = point.next;
	        if (point == null) {
	            return head;
	        }
	        i--;
	    }
	    ListNode temp = point.next;
	     //将子链表断开
	    point.next = null;

	    //倒置子链表，并接受新的头结点
	    ListNode new_head = reverse(head);

	    //head 其实是倒置链表的尾部，然后我们将后边的倒置结果接过来就可以了
	    //temp 是链表断开后的头指针，可以参考解法一的图示
	    head.next = swapPairs(temp);
	    return new_head;
	}
	public ListNode reverse(ListNode head) {
	    ListNode current_head = null;
	    while (head != null) {
	        ListNode next = head.next;
	        head.next = current_head;
	        current_head = head;
	        head = next;
	    }
	    return current_head;
	}
}
 */



 