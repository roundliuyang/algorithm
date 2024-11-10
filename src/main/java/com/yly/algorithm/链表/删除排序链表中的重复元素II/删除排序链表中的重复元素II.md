# 删除排序链表中的重复元素II



## 题目描述

给定一个已排序的链表的头 `head` ， *删除原始链表中所有重复数字的节点，只留下不同的数字* 。返回 *已排序的链表* 。

 

**示例 1：**

![img](删除排序链表中的重复元素II.assets/linkedlist1.jpg)

```
输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]
```

**示例 2：**

![img](删除排序链表中的重复元素II.assets/linkedlist2.jpg)

```
输入：head = [1,1,1,2,3]
输出：[2,3]
```

 

**提示：**

- 链表中节点数目在范围 `[0, 300]` 内
- `-100 <= Node.val <= 100`
- 题目数据保证链表已经按升序 **排列**



## 解题思路



## 代码

```java
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
```

