package com.yly.algorithm.DFS.两个二叉树是否相等;

import com.yly.algorithm.DFS.TreeNode;

/**
 * 解法参考二叉树是否为镜像
 */
class 两个二叉树是否相等 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return check(p, q);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.left) && check(p.right, q.right);
    }
}
