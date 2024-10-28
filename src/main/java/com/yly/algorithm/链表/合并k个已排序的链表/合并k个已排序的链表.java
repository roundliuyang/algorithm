package com.yly.algorithm.链表.合并k个已排序的链表;

import com.yly.algorithm.链表.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 合并k个已排序的链表 {
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> l = new ArrayList<Integer>();
        //存到数组
        for (ListNode ln : lists) {
            while (ln != null) {
                l.add(ln.val);
                ln = ln.next;
            }
        }
        //数组排序
        Collections.sort(l);
        //存到链表
        ListNode head = new ListNode(0);
        ListNode h = head;
        for (int i : l) {
            ListNode t = new ListNode(i);
            h.next = t;
            h = h.next;
        }
        h.next = null;
        return head.next;
    }
}
