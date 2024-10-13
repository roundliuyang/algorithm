package com.yly.algorithm.链表.链表求和;

import com.yly.algorithm.链表.ListNode;

/**
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 * <p>
 * 如果是正序则先翻转链表，再用上述方法
 */
public class 链表求和 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2, result = new ListNode(0);
        ListNode p = result;
        int carr = 0;

        while (p1 != null || p2 != null || carr > 0) {
            int sum = carr;
            sum += p1 == null ? 0 : p1.val;
            sum += p2 == null ? 0 : p2.val;

            p.next = new ListNode(sum % 10);
            p = p.next;

            carr = sum / 10;

            if (p1 != null) {
                p1 = p1.next;
            }

            if (p2 != null) {
                p2 = p2.next;
            }
        }

        return result.next;
    }
}


 