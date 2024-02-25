package com.yly.algorithm.BFS;

import java.util.HashSet;
import java.util.LinkedList;

public class 岛屿的个数 {
    /**
     * 题目描述，见笔记
     * 解：竟然和模板一模一样
     */

    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;
        HashSet<Integer> visited = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited.contains(i * m + j) && grid[i][j]) {
                    bfs(grid, i, j, visited);
                    ans = ans + 1;
                }
            }
        }
        return ans;
    }


    public void bfs(boolean[][] grid, int x, int y, HashSet<Integer> visited) {
        if (!grid[x][y]) {
            return;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
//        int n = grid.length;
        int m = grid[0].length;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(x * m + y);   // (x,y) ===> (x * m +y)
        visited.add(x * m + y);

        // mark it as visited
        while (!queue.isEmpty()) {
            Integer now = queue.poll();
            int now_x = now / m;
            int now_y = now % m;
            for (int i = 0; i < 4; i++) {
                int next_x = now_x + dx[i];
                int next_y = now_y + dy[i];
                if (isValid(next_x, next_y, grid, visited)) {
                    visited.add(next_x * m + next_y);
                    queue.offer(next_x * m + next_y);
                }
            }
        }
    }

    public boolean isValid(int x, int y, boolean[][] grid, HashSet<Integer> visited) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return false;
        }
        if (!grid[x][y]) {
            return false;
        }
        if (visited.contains(x * grid[0].length + y)) {
            return false;
        }
        return true;
    }
}
