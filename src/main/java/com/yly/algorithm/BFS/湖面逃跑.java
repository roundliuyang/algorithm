package com.yly.algorithm.BFS;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Albert被困在结冰的湖面上。 他想知道他是否能回到岸上。 他目前在雪堆上，这给了他一些摩擦力。但一旦他踏进冰面，他会朝同一方向滑行，直到撞上另一个雪堆。
 * 冰面上也有他必须避开的危险的洞。
 * Albert的小狗，Kuna，也被困在一个不同的雪堆上。 Albert能找到他的小狗并把它带到岸上吗？
 * Albert只能水平和垂直移动。 他最终需要来到岸边，离开湖面
 * 输入包括以下参数：
 * <p>
 * side_length: 湖面的长度（这是一个正方形）
 * lake_grid: 一个二维数组代表湖面，其中0代表冰面，1代表雪堆，-1代表洞
 * albert_row: Albert所在的雪堆的行
 * albert_column: Albert所在的雪堆的列
 * kuna_row: Kuna所在的雪堆的行
 * kuna_column: Kuna所在的雪堆的列
 * <p>
 * 示例 1:
 * 输入:
 * 7
 * [[0,0,0,0,0,0,0],[0,0,-1,0,0,0,0],[0,0,1,-1,0,-1,0],[-1,0,1,0,0,0,0],[0,1,1,0,0,1,0],[-1,0,-1,0,-1,0,0],[0,0,0,0,0,0,0]]
 * 4
 * 1
 * 3
 * 2
 * 输出:
 * true
 * 说明：
 * 如图所示。黄色格子是Albert的位置，红色格子是Kuna的位置。Albert可以向右走到(4,2)，向上走到(3,2)，然后向右走，离开湖面。
 */
public class 湖面逃跑 {

    /**
     * 不会
     */
    public boolean lakeEscape(int side_length, int[][] lake_grid, int albert_row, int albert_column, int kuna_row, int kuna_column) {
        int dx[] = {0, 1, 0, -1};
        int dy[] = {1, 0, -1, 0};
        int[][][] visit = new int[2][side_length][side_length];
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        Queue<Integer> qc = new LinkedList<>();
        qc.offer(0);
        qx.offer(albert_row);
        qy.offer(albert_column);
        while (!qx.isEmpty()) {
            int cc = qc.poll();
            int cx = qx.poll();
            int cy = qy.poll();
            if (visit[cc][cx][cy] != 0) continue;
            visit[cc][cx][cy] = 1;
            for (int i = 0; i < 4; i++) {
                int nowx = cx, nowy = cy;
                boolean flag1 = true, flag2 = false, flag3 = false;
                for (int j = 0; j <= side_length; j++) {
                    nowx += dx[i];
                    nowy += dy[i];
                    if (nowx == kuna_row && nowy == kuna_column) flag3 = true;
                    if (nowx < 0 || nowx >= side_length || nowy < 0 || nowy >= side_length) {
                        flag2 = true;
                        break;
                    }
                    if (lake_grid[nowx][nowy] == -1) {
                        flag1 = false;
                        break;
                    }
                    if (lake_grid[nowx][nowy] == 1) break;
                }
                if (flag2) {
                    if (cc == 1 || flag3) return true;
                } else if (flag1) {
                    if (flag3) {
                        qc.offer(1);
                        qx.offer(nowx);
                        qy.offer(nowy);
                    } else {
                        qc.offer(cc);
                        qx.offer(nowx);
                        qy.offer(nowy);
                    }
                }
            }
        }
        return false;
    }
}
