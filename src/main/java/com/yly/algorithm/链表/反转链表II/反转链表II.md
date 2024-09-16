# 反转链表II

## 题目

给你单链表的头指针 `head` 和两个整数 `left` 和 `right` ，其中 `left <= right` 。请你反转从位置 `left` 到位置 `right` 的链表节点，返回 **反转后的链表** 。

 

**示例 1：**

![img](反转链表II.assets/rev2ex2.jpg)

```
输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
```

**示例 2：**

```
输入：head = [5], left = 1, right = 1
输出：[5]
```



## 解题思路

```
思路
left到right之间的node反转后，如何让1指向4,2指向5是关键中的关键，也就是有一点绕而已
```

## 代码

```java
public ListNode reverseBetween(ListNode head, int left, int right) {
    if (head == null || left == right) {
        return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode preLeft = dummy;

    // Move preLeft to the node before left
    for (int i = 0; i < left - 1; i++) {
        preLeft = preLeft.next;
    }

    ListNode pre = preLeft.next;
    ListNode curr = pre.next;

    // Reverse the nodes between left and right
    for (int i = 0; i < right - left; i++) {
        ListNode nextTemp = curr.next;
        curr.next = pre;
        pre = curr;
        curr = nextTemp;
    }

    // Connect the reversed part with the rest of the list
    preLeft.next.next = curr;
    preLeft.next = pre;

    return dummy.next;
}
```

