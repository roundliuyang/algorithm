package com.yly.algorithm.并查集;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * 请你设计一个数据结构，支持以下两种操作：
 * • void addEdge(int a, int b)：在编号为a的点和编号为 b的点之间链接一条边。保证不会出现自环和重边。
 * • bool isValidTree()：判断当前已经出现的点和边是否能形成一棵树。
 *
 * 一个图是树的充要条件：
 * 1、图上有n个点、并且仅有n-1条边
 * 2、n个点是连通的（属于同一个连通块） <=====> 等价于有环无环
 * <p>
 * 此处用的是 有环和无环
 *
 *
 * 使用路径压缩，每次合并最坏O(n)  总时间复杂度：O(n ^ 2)
 * 使用路径压缩O(n)，每次合并为O(1)总时间复杂度：O(n)
 * 并查集中点的数量为n，空间复杂度O(n)
 */
public class 图是否是树II {
    UnionFind uf;
    boolean hasCycle;
    int numOfEdges;

    public 图是否是树II() {
        this.uf = new UnionFind();
        this.hasCycle = false;
        this.numOfEdges = 0;
    }

    public void addEdge(int x, int y) {
        uf.add(x);
        uf.add(y);

        numOfEdges++;
        if (uf.isConnected(x, y)) {
            hasCycle = true;
        }
        uf.merge(x, y);
    }

    public boolean isValidTree() {
        if (uf.father.size() != numOfEdges) {
            return false;
        }
        return !hasCycle;
    }
}

class UnionFind {
    /**
     * 父亲表示法
     * 用一个哈希表存放每个节点的父亲father.put(x, y) father[x] = y
     * 代表x 的父节点是y
     */
    public Map<Integer, Integer> father;

    public UnionFind() {
        // 初始化父指针
        this.father = new HashMap<Integer, Integer>();
    }

    public void add(int x) {
        // 点如果已经出现，操作无效
        if (father.containsKey(x)) {
            return;
        }

        // 初始化点的父亲为 空对象null
        father.put(x, null);
    }

    /**
     * 查询所在集合(find)
     * 找到根节点来代表这个集合
     */
    public int find(int x) {
        // 指针 root 指向被查找的点 x
        // 不断找到 root  的父亲
        // 知道 root 指向 x 的根节点
        int root = x;
        while (father.get(root) != null) {
            root = father.get(root);
        }
        return root;
    }

    /**
     * 路径压缩优化
     * @param x
     * @return
     */
//    public int find2(int x) {
//        // 指针 root 指向被查找的点 x
//        // 不断找到 root  的父亲
//        // 直到 root 指向 x 的根节点
//        int root = x;
//        while (father.get(root) != null) {
//            root = father.get(root);
//        }
//
//        // 将路径上所有点指向根节点
//        while(x != root){
//            // 暂存 x 原本的父亲
//            // 将 x 指向根节点
//            // x指针 上移至 x 的父节点
//            Integer originalFather = father.get(x);
//            father.put(x,root);
//            x = originalFather;
//        }
//        return root;
//    }


    /**
     * 合并两个集合(merge)
     * 找到两个集合中的两个根节点
     */
    public void merge(int x, int y) {
        // 找到两个节点的根
        int rootX = find(x);
        int rootY = find(y);

        // 如果根不是同一个则连接
        if (rootX != rootY) {
            father.put(rootX, rootY);
        }
    }

    /**
     * 判断两点是否在同一个集合isConnected(x, y)
     * 以下三种说法均为同一个含义
     * •两点是否在同一个集合
     * •两点是否属于同一个连通块
     * •两点是否连通
     * •先找到两个点的根节点，判断根节点是否相等即可判断两点是否在同一个集合isConnected(x, y)
     */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

}
