package com.yly.algorithm.BFS.BFS进阶与最短路径算法;


import java.util.*;

/**
 * 有一个一维的棋盘，起点在棋盘的最左侧，终点在棋盘的最右侧，棋盘上有几个位置是跟其他的位置相连的，即如果A与B相连，则当棋子落在位置A时,
 * 你可以选择是否不投骰子，直接移动棋子从A到B。并且这个连接是单向的，即不能从B移动到A，现在给定这个棋盘的长度length和位置的相连情况connections，
 * 你有一个六面的骰子(点数1-6)，最少需要丢几次才能到达终点。
 * 下标从 1 开始
 * length > 1
 * 起点不与任何其他位置连接
 * connections[i][0] < connections[i][1]
 */
public class 飞行棋I {

    public static void main(String[] args) {
        飞行棋I cla = new 飞行棋I();
        int[][] arr = {{2, 8}, {6, 9}};
        System.out.println(cla.modernLudo2(15, arr));
    }


    /**
     * 方法1：BFS+BFS ---------------------------------------------------------------------------------------------------
     */

    public int modernLudo(int length, int[][] connections) {
        // 构建图
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        Map<Integer, Integer> distance = new HashMap<>();
        distance.put(1, 0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int limit = Math.min(node + 7, length + 1);
            for (int neighbor = node + 1; neighbor < limit; neighbor++) {
                List<Integer> connectedNodes = getUnvisitedNodes(graph, distance, neighbor);
                for (int connectedNode : connectedNodes) {
                    distance.put(connectedNode, distance.get(node) + 1);
                    queue.offer(connectedNode);
                }
            }
        }

        return distance.get(length);
    }

    private Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            graph.get(from).add(to);
        }
        return graph;
    }

    private List<Integer> getUnvisitedNodes(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> distance, int node) {
        List<Integer> unVisitedNodes = new ArrayList();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            if (distance.containsKey(currentNode)) {
                continue;
            }
            unVisitedNodes.add(currentNode);
            for (int neighbor : graph.get(currentNode)) {
                if (!distance.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    unVisitedNodes.add(neighbor);
                }
            }
        }
        return unVisitedNodes;
    }


    /**
     * 方法2：两个队列交替 ---------------------------------------------------------------------------------------------------
     */


    public int modernLudo2(int length, int[][] connections) {
        // 构建图
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);
        List<Integer> queue = new ArrayList<>();
        queue.add(1);
        Map<Integer, Integer> distance = new HashMap<>();
        distance.put(1, 0);

        while (!queue.isEmpty()) {
            List<Integer> nextQueue = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                int node = queue.get(i);
                for (int directNode : graph.get(node)) {
                    if (distance.containsKey(directNode)) {
                        continue;
                    }
                    distance.put(directNode, distance.get(node));
                    queue.add(directNode);
                }
            }

            for (int i = 0; i < queue.size(); i++) {
                int node = queue.get(i);
                int limit = Math.min(node + 7, length + 1);
                for (int nextNode = node + 1; nextNode < limit; nextNode++) {
                    if (distance.containsKey(nextNode)) {
                        continue;
                    }
                    distance.put(nextNode, distance.get(node) + 1);
                    nextQueue.add(nextNode);
                }
            }
            queue = nextQueue;
        }
        return distance.get(length);
    }

    /**
     * 方法3：SPFA ---------------------------------------------------------------------------------------------------
     */

    public int modernLudo3(int length, int[][] connections) {
        // 构建图
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        Map<Integer, Integer> distance = new HashMap<>();
        distance.put(1, 0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nextNode : graph.get(node)) {
                if (distance.get(nextNode) > distance.get(node)) {
                    distance.put(nextNode, distance.get(node));
                    queue.offer(nextNode);
                }
            }
            int limit = Math.min(node + 7, length + 1);
            for (int nextNode = node + 1; nextNode < limit; nextNode++) {
                if (distance.get(nextNode) > distance.get(node) + 1) {
                    distance.put(nextNode, distance.get(node) + 1);
                    queue.offer(nextNode);
                }
            }
        }
        return distance.get(length);
    }


    /**
     * 方法4：动态规划(DP) ---------------------------------------------------------------------------------------------------
     */

    public int modernLudo4(int length, int[][] connections) {
        // 构建图
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);
        int[] dp = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[length] = 0;
        for (int i = length - 1; i > 0; i--) {
            int limit = Math.min(i + 7, length + 1);
            for (int j = i + 1; j < limit; j++) {
                if (dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }

            for (int j : graph.get(i)) {
                dp[i] = Math.min(dp[i], dp[j]);
            }

        }
        return dp[1];
    }
}
