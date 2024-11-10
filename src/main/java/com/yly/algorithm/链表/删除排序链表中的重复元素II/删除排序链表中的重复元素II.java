package com.yly.algorithm.链表.删除排序链表中的重复元素II;

import com.yly.algorithm.链表.ListNode;

/**
 * leetcode 82
 * 如果是链表 所有的元素只出现1次，则第20行修改为 return deleteDuplicates(head);即可
 */
public class 删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        //如果头结点和后边的节点相等
        if (head.next != null && head.val == head.next.val) {
            //跳过所有重复数字
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            //将所有重复数字去掉后，进入递归
            return deleteDuplicates(head.next);
            //头结点和后边的节点不相等
        } else {
            //保留头结点，后边的所有节点进入递归
            head.next = deleteDuplicates(head.next);
        }
        //返回头结点
        return head;
    }
}
