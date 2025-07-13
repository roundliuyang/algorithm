package com.yly.algorithm.链表.合并k个已排序的链表;

import com.yly.algorithm.链表.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 合并k个已排序的链表 {
    // 方法一： 作弊风格
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

    // 方法二： 调用多次两个链表合并
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(0);
        ListNode ans = h;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                h = h.next;
                l1 = l1.next;
            } else {
                h.next = l2;
                h = h.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            h.next = l2;
        }
        if (l2 == null) {
            h.next = l1;
        }
        return ans.next;
    }

    // 方法三： 分治合并
    // 将 k 个链表配对并将同一对中的链表合并,第一轮合并以后， k 个链表被合并成了k/2个链表，然后是k/4个链表，k/8个链表
    public ListNode mergeKLists3(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }
}

