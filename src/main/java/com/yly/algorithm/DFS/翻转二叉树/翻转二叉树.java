package com.yly.algorithm.DFS.翻转二叉树;

import com.yly.algorithm.DFS.TreeNode;

public class 翻转二叉树 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
