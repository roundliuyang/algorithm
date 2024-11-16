package com.yly.algorithm.DFS.二叉树的之字形层序遍历;

import com.yly.algorithm.DFS.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的之字形层序遍历 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        DFS(root, 0, ans);
        return ans;
    }

    private void DFS(TreeNode root, int level, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
        if (ans.size() <= level) {
            ans.add(new ArrayList<Integer>());
        }
        if ((level % 2) == 0) {
            ans.get(level).add(root.val); //添加到末尾
        } else {
            ans.get(level).add(0, root.val); //添加到头部
        }

        DFS(root.left, level + 1, ans);
        DFS(root.right, level + 1, ans);
    }
}
