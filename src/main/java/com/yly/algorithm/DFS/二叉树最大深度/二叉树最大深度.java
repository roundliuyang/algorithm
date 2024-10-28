package com.yly.algorithm.DFS.二叉树最大深度;

import com.yly.algorithm.DFS.TreeNode;

public class 二叉树最大深度 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return Math.max(left_height, right_height) + 1;
        }
    }
}
