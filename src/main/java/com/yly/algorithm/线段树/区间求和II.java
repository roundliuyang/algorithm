package com.yly.algorithm.线段树;


/**
 * todo 是在是太难了，吐了
 * 给出一个整数数组 A，和两种操作：询问query 和 更新modify。
 * 对于 modify：
 * •包括两个整数 index 和 value
 * •代表将 A[index] 的值修改为 value
 * 对于 query：
 * •包括两个整数 start 和 end
 * •求出 A[start] + A[start + 1] + … + A[end – 1] + A[end]
 * •每次 query 都要根据 modify 操作之后的新数组计算
 * <p>
 * <p>
 * 前缀和难以应对数组的频繁修改
 * 使用前缀和可以进行离线查询修改数组后更新前缀和的时间复杂度为 O(n)
 * 总时间复杂度将达到 O(n * m)
 * 需要高效的在线查询型数据结构
 */
public class 区间求和II {
    SegmentTree st;

    public 区间求和II(int[] A) {
        st = new SegmentTree(A, 0, A.length - 1);
    }

    public long query(int start, int end) {
        return st.query(st.root, start, end);
    }

    public void modify(int index, int value) {
        st.modify(st.root, index, value);
    }
}

class SegmentTreeNode {
    public int start, end;
    public SegmentTreeNode left, right;
    public int intervalSum;

    public SegmentTreeNode(int start, int end, int x) {
        this.start = start;
        this.end = end;
        this.intervalSum = x;
        this.left = null;
        this.right = null;
    }
}

class SegmentTree {
    public SegmentTreeNode root;

    public SegmentTree(int[] nums, int start, int end) {
        root = construct(nums, start, end);
    }

    public SegmentTreeNode construct(int[] nums,
                                     int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode newNode = new SegmentTreeNode(start, end, 0);
        if (start != end) {
            int mid = start + (end - start) / 2;
            newNode.left = construct(nums, start, mid);
            newNode.right = construct(nums, mid + 1, end);
            if (newNode.left != null) {
                newNode.intervalSum += newNode.left.intervalSum;
            }
            if (newNode.right != null) {
                newNode.intervalSum += newNode.right.intervalSum;
            }
        } else {
            newNode.intervalSum = nums[start];
        }
        return newNode;
    }

    public void modify(SegmentTreeNode root, int index, int value) {
        if (root.start == index && root.end == index) {
            root.intervalSum = value;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (root.start <= index && index <= mid) {
            modify(root.left, index, value);
        }
        if (mid < index && index <= root.end) {
            modify(root.right, index, value);
        }
        root.intervalSum = root.left.intervalSum + root.right.intervalSum;
    }

    public int query(SegmentTreeNode root, int start, int end) {
        if (root.start == start && root.end == end) {
            return root.intervalSum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        int result = 0;
        if (start <= mid && mid < end) {
            result += query(root.left, start, mid);
            result += query(root.right, mid + 1, end);
        } else if (start > mid) {
            result += query(root.right, start, end);
        } else if (end <= mid) {
            result += query(root.left, start, end);
        }
        return result;
    }

}
