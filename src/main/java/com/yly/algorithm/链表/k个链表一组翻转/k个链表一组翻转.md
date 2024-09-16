# k个链表一组翻转

## 题目

给你链表的头节点 `head` ，每 `k` 个节点一组进行翻转，请你返回修改后的链表。

`k` 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 `k` 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

**示例 1：**

![img](k个链表一组翻转.assets/reverse_ex1.jpg)

```
输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
```

**示例 2：**

![img](k个链表一组翻转.assets/reverse_ex2.jpg)

```
输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
```

 

**提示：**

- 链表中的节点数目为 `n`
- `1 <= k <= n <= 5000`
- `0 <= Node.val <= 1000`

## 解题思路

已head = [1,2,3,4,5], k = 3 为例。其实只需三步就ok了

```
在第k个节点断开子链
这个链表进行反转
head.next = xxx (head 其实是倒置链表的尾部，然后我们将后边的倒置结果接过来就可以了)
```





## 代码

```java
public class k个链表一组翻转 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;
        ListNode point = head;
        //找到子链表的尾部
        int i = k;
        while (i - 1 > 0) {
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
        head.next = reverseKGroup(temp, k);
        return new_head;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
```

