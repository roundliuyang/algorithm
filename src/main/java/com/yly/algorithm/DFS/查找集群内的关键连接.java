package com.yly.algorithm.DFS;


import java.util.*;

/**
 数据中心有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号。
 它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群，其中连接 connections 是无向的。
 从形式上讲，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接。任何服务器都可以直接或者间接地通过网络到达任何其他服务器。
 「关键连接」是在该集群中的重要连接，也就是说，假如我们将它移除，便会导致某些服务器无法访问其他服务器。
 请你以任意顺序返回该集群内的所有 「关键连接」。当你返回的时候，对于每一条连接，你需要保证 index1 < index2。例如，如果答案是[[1,2],[3,4]]，
 你可以返回[[3,4],[1,2]]，但是[[2,1],[3,4]]是不合法答案，

     1 <= n <= 10^5
     n-1 <= connections.length <= 10^5
     connections[i][0] != connections[i][1]
     不存在重复的连接
     在Java语言中，由于栈比较浅，当你通过57%的数据然后RE的时候，你可以认为你已经通过了此题。
 */
public class 查找集群内的关键连接 {
    private Map<Integer, HashSet<Integer>> initGraph(List<List<Integer>> connections) {
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();

        for(List<Integer> connection:connections) {
            graph.putIfAbsent(connection.get(0), new HashSet<>());
            graph.get(connection.get(0)).add(connection.get(1));
            graph.putIfAbsent(connection.get(1), new HashSet<>());
            graph.get(connection.get(1)).add(connection.get(0));
        }
        return graph;
    }

    private int dfs(int child, int father, int step, int[] steps, Map<Integer, HashSet<Integer>> graph,
                    List<List<Integer>> res) {
        int curStep = step + 1;
        steps[child] = curStep;
        for(int connection:graph.get(child)) {
            if(connection == father) {
                continue;
            } else if(steps[connection] == -1) {
                steps[child] = Math.min(steps[child], dfs(connection, child, curStep, steps, graph, res));
            } else {
                steps[child] = Math.min(steps[child], steps[connection]);
            }
        }

        if(child != 0 && steps[child] == curStep) {
            List<Integer> critial = new ArrayList<>();
            if(father > child) {
                int tmp = child;
                child = father;
                father = tmp;
            }

            critial.add(father);
            critial.add(child);
            res.add(critial);
        }

        return steps[child];
    }

    /**
     * 属实是不会
     */
    public List<List<Integer>> criticalConnectionsinaNetwork(int n, List<List<Integer>> connections) {
        Map<Integer, HashSet<Integer>> graph = initGraph(connections);
        int[] steps = new int[n];
        Arrays.fill(steps, -1);

        List<List<Integer>> res = new ArrayList<>();
        dfs(0, -1, 0, steps, graph, res);
        return res;
    }
}
