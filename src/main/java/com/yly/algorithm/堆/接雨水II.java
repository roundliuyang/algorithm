package com.yly.algorithm.堆;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给出 n * m 个非负整数，代表一张X轴上每个区域为 1 * 1 的 2d 海拔图, 计算这个海拔图最多能接住多少（体积）雨水。
 * 例如，给定一个 5*4 的矩阵：
 * 输入:  [[12,13,0,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]]
 * 输出:  14
 * <p>
 * 思路整理
 * •用堆存放所有的最外层格子
 * •每次从堆中拿出最低高度的格子(堆顶)
 * •找到这个格子的四个邻居
 * •只有邻居比自己低的情况，邻居才能够积水
 * •计算邻居的积水量，并把邻居放入堆中
 * •不断执行到堆空，累加的存水量就是答案
 * <p>
 * 复杂度分析
 * 使用了一个堆，堆中的元素不会超过 n*m 个
 * 每个元素都会进入、弹出一次堆，总时间复杂度 O(n*m*log(n*m))
 * 空间复杂度 O(n * m)
 */
public class 接雨水II {
    static int[][] DIRECTIONS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public int trapRainWater(int[][] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int n = heights.length;
        int m = heights[0].length;

        boolean[][] visited = new boolean[n][m];
        // 初始化一个小顶堆，按Grid 的高度由低到高排序
        PriorityQueue<Grid> heap = new PriorityQueue<>(
                new Comparator<Grid>() {
                    @Override
                    public int compare(Grid a, Grid b) {
                        return a.height - b.height;
                    }
                }
        );
        // 将最外层的所有格子加入堆中
        for (int i = 0; i < n; i++) {
            heap.offer(new Grid(i, 0, heights[i][0]));
            heap.offer(new Grid(i, m - 1, heights[i][m - 1]));
            visited[i][0] = true;
            visited[i][m - 1] = true;
        }

        for (int i = 1; i < m - 1; i++) {
            heap.offer(new Grid(0, i, heights[0][i]));
            heap.offer(new Grid(n - 1, i, heights[n - 1][i]));
            visited[0][i] = true;
            visited[n - 1][i] = true;
        }

        int water = 0;
        while (!heap.isEmpty()) {
            // 找到堆中高度最小的格子，视为木桶短板
            Grid shortest = heap.poll();
            // 对短板四周的格子计算存水量
            for (int[] direction : DIRECTIONS) {
                int x = shortest.x + direction[0];
                int y = shortest.y + direction[1];
                // 判断坐标是否越界 和邻居是否被访问过
                if (!isValid(x, y, visited)) {
                    continue;
                }
                // 只有邻居的高度比现在的短板还小，邻居才会积水
                if (shortest.height > heights[x][y]) {
                    water += shortest.height - heights[x][y];
                }
                // 将邻居加入堆中，并将邻居的高度设置为较大者
                int height = Math.max(shortest.height, heights[x][y]);
                heap.offer(new Grid(x, y, height));
                visited[x][y] = true;
            }
        }
        return water;
    }

    boolean isValid(int x, int y, boolean[][] visited) {
        if (x < 0 || x >= visited.length) {
            return false;
        }
        if (y < 0 || y >= visited[0].length) {
            return false;
        }
        return !visited[x][y];
    }

    // 测试
//    public static void main(String[] args) {
//        int[][] matrix = {
//                {12, 13, 0, 12},
//                {13, 4, 13, 12},
//                {13, 8, 10, 12},
//                {12, 13, 12, 12},
//                {13, 13, 13, 13}
//        };
//        trapRainWater(matrix);
//    }
}


class Grid {
    // 坐标为 （x,y） 的格子，高度为 height
    int x;
    int y;
    int height;

    public Grid() {
    }

    public Grid(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }
}
