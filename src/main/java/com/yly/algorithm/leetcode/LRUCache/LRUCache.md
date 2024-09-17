# LRUCache

## 题目

请你设计并实现一个满足 [LRU (最近最少使用) 缓存](https://baike.baidu.com/item/LRU) 约束的数据结构。

实现 `LRUCache` 类：

- `LRUCache(int capacity)` 以 **正整数** 作为容量 `capacity` 初始化 LRU 缓存
- `int get(int key)` 如果关键字 `key` 存在于缓存中，则返回关键字的值，否则返回 `-1` 。
- `void put(int key, int value)` 如果关键字 `key` 已经存在，则变更其数据值 `value` ；如果不存在，则向缓存中插入该组 `key-value` 。如果插入操作导致关键字数量超过 `capacity` ，则应该 **逐出** 最久未使用的关键字。

函数 `get` 和 `put` 必须以 `O(1)` 的平均时间复杂度运行。

**示例：**

```
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
```

**提示：**

- `1 <= capacity <= 3000`
- `0 <= key <= 10000`
- `0 <= value <= 105`
- 最多调用 `2 * 105` 次 `get` 和 `put`



## 解题思路

在双向链表中，删除某个节点很简单，因为节点有指向前驱和后继的指针。但是在单向链表中，删除一个节点需要知道它的前驱节点，否则无法直接修改前驱节点的 `next` 指针。通过 `keyToPrev`，我们可以在 `O(1)` 时间内找到某个节点的前驱，从而实现高效的删除和移动操作。

在你的 `LRUCache` 实现中，`keyToPrev` 是一个 `Map<Integer, ListNode>` 类型的哈希表，它用于记录链表节点的前驱节点（即前一个节点）。



**`keyToPrev` 的意义：**

- **Key**: 缓存中的某个 `key`

- **Value**: 链表中该 `key` 对应节点的**前一个节点**。

  

**为什么需要 `keyToPrev`:**

`keyToPrev` 的作用是为了在链表中能够快速找到某个节点的前驱节点，这样可以方便地将节点从链表中移除，并将其移动到链表尾部。

例如，`moveToTail` 方法中，需要从链表中删除某个节点，并将其移动到尾部时，首先通过 `keyToPrev` 找到该节点的前驱，然后调整指针来完成移动。

## 代码

```java
/**
 * 用链表存放 cache，表尾的点是 most recently，表头的点是 least recently used
 */
public class LRUCache {
    // 单链表节点
    class ListNode {
        public int key;
        public int val;
        public ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }

    // cache 的最大容量
    private int capacity;
    // cache 当前存储的容量
    private int size;
    // 单链表的 dummy 头
    private ListNode dummy;
    // 单链表尾
    private ListNode tail;
    /**
     * 它用于记录链表节点的前驱节点
     * Key: 缓存中的某个 key, Value: 链表中该 key 对应节点的前一个节点
     */
    private Map<Integer, ListNode> keyToPrev;

    // 构造函数
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.keyToPrev = new HashMap<>();
        // dummy 点的 key 和 value 随意
        this.dummy = new ListNode(0, 0);
        this.tail = this.dummy;
    }


    public int get(int key) {
        // 如果这个 key 根本不存在于缓存，返回 -1
        if (!keyToPrev.containsKey(key)) {
            return -1;
        }
        // 这个 key 刚刚被访问过，因此 key 节点应当被移动到链表尾部
        moveToTail(key);
        // key 节点被移动到链表尾部，返回尾部的节点值，即 tail.val
        return tail.val;
    }


    public void set(int key, int value) {
        // 如果 key 已经存在，更新 keyNode 的 value
        if (get(key) != -1) {
            ListNode prev = keyToPrev.get(key);
            prev.next.val = value;
            return;
        }
        // 如果 key 不存在于 cache 且 cache 未超上限
        // 再结尾存入新的节点
        if (size < capacity) {
            size++;
            ListNode curt = new ListNode(key, value);
            tail.next = curt;
            keyToPrev.put(key, tail);
            tail = curt;
            return;
        }
        // 如果超过上限，删除链表头
        ListNode first = dummy.next;
        keyToPrev.remove(first.key);
        first.key = key;
        first.val = value;
        keyToPrev.put(key, dummy);
        moveToTail(key);
    }

    // 将 key 节点移动到尾部
    private void moveToTail(int key) {
        ListNode prev = keyToPrev.get(key);
        ListNode curt = prev.next;
        // 如果 key 节点已经再尾部，无需移动
        if (tail == curt) {
            return;
        }
        // 从链表中删除 key 节点
        prev.next = prev.next.next;
        tail.next = curt;
        curt.next = null;
        keyToPrev.put(prev.next.key, prev);
        
        keyToPrev.put(curt.key, tail);
        tail = curt;
    }
}
```

