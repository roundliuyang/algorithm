package com.yly.algorithm.DFS.二叉树最大宽度;

import com.yly.algorithm.DFS.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class 二叉树最大宽度 {
    /**
     * 存储到目前为止找到的二叉树的最大宽度。
     */
    int ans;
    /**
     * 一个 Map<Integer, Integer>，用于记录每一层（深度）的最左边节点的位置
     */
    Map<Integer, Integer> left;

    public int widthOfBinaryTree(TreeNode root) {
        ans = 0;
        left = new HashMap();
        dfs(root, 0, 0);
        return ans;
    }

    /**
     * @param root 当前的树节点
     * @param depth 当前节点的深度/层数（根节点的深度为 0）
     * @param pos 当前节点在这一层的位置，用 0 表示根节点的位置，左子节点的位置为 2*pos，右子节点的位置为 2*pos + 1
     */
    public void dfs(TreeNode root, int depth, int pos) {
        if (root == null) {
            return;
        }
        left.computeIfAbsent(depth, x -> pos);
        ans = Math.max(ans, pos - left.get(depth) + 1);
        dfs(root.left, depth + 1, 2 * pos);
        dfs(root.right, depth + 1, 2 * pos + 1);
    }
}
