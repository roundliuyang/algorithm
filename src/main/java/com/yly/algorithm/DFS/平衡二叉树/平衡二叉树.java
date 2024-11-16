package com.yly.algorithm.DFS.平衡二叉树;

import com.yly.algorithm.DFS.TreeNode;

public class 平衡二叉树 {
    public boolean isBalanced(TreeNode root) {
        //它是一棵空树
        if (root == null) {
            return true;
        }
        //它的左右两个子树的高度差的绝对值不超过1
        int leftDepth = getTreeDepth(root.left);
        int rightDepth = getTreeDepth(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }
        //左右两个子树都是一棵平衡二叉树
        return isBalanced(root.left) && isBalanced(root.right);

    }

    private int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getTreeDepth(root.left);
        int rightDepth = getTreeDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
