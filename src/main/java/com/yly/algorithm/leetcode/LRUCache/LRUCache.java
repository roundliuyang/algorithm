package com.yly.algorithm.leetcode.LRUCache;


import java.util.HashMap;
import java.util.Map;

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


