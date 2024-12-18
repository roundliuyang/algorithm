package com.yly.algorithm.DFS.机器人的运动范围;

public class 机器人的运动范围 {
    int m, n, k;
    boolean[][] visited;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.visited = new boolean[m][n];
        return dfs(0, 0, 0, 0);
    }

    /**
     * @param i  当前所在网格的 行坐标。
     * @param j  当前所在网格的 列坐标。
     * @param si 当前行坐标 i 的 数位之和。
     * @param sj 当前列坐标 j 的 数位之和。
     * @return
     */
    public int dfs(int i, int j, int si, int sj) {
        if (i >= m || j >= n || k < si + sj || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        return 1 +
                dfs(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj) +
                dfs(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);
    }
}




 