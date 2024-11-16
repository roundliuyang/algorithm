package com.yly.algorithm.DFS.子结构判断;

import com.yly.algorithm.DFS.TreeNode;

public class 子结构判断 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return fun(A, B) ||
                isSubStructure(A.left, B) ||
                isSubStructure(A.right, B);

    }

    public boolean fun(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        return (a.val == b.val) &&
                fun(a.left, b.left) &&
                fun(a.right, b.right);
    }
}
