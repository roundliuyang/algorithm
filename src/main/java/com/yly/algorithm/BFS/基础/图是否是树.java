package com.yly.algorithm.BFS.基础;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 给出点的数量n 和一个无向边列表edges，
 * 判断该列表连接成的图是不是一棵树。
 * n = 5，edges = [[0, 1], [0, 2], [0, 3], [1, 4]] --> true
 * n = 5，edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]] --> false
 * <p>
 * 图(可能有环)VS 树(一定无环)
 * 一个图是树的充要条件：
 * 1、图上有n个点、并且仅有n-1条边
 * 2、n个点是连通的（属于同一个连通块） <=====> 等价于有环无环
 */
public class 图是否是树 {

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        // 建立图
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // BFS
        LinkedList<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        // 起点从0开始
        queue.offer(0);
        visited.add(0);
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for (Integer neighbor : graph.get(poll)) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                visited.add(neighbor);
                queue.offer(neighbor);
            }
        }
        return visited.size() == n;
    }
}
