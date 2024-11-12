package com.yly.algorithm.链表.随机链表的复制;

import java.util.HashMap;

/**
 * 从 头 指针开始遍历整个图。
 * 当我们遍历到某个点时，如果我们已经有了当前节点的一个拷贝，我们不需要重复进行拷贝。
 * 如果我们还没拷贝过当前节点，我们创造一个新的节点，并把该节点放到已访问字典中，即：
 * visited_dictionary[current_node] = cloned_node_for_current_node.
 * 我们针对两种情况进行回溯调用：一个顺着 random 指针调用，另一个沿着 next 指针调用。
 * 步骤 1 中将 random 和 next 指针分别红红色和蓝色标注。然后我们分别对两个指针进行函数递归调用：
 */
public class 随机链表的复制 {
    HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }
        Node node = new Node(head.val, null, null);
        this.visitedHash.put(head, node);
        node.next = this.copyRandomList(head.next);
        node.random = this.copyRandomList(head.random);
        return node;
    }
	  

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}




 