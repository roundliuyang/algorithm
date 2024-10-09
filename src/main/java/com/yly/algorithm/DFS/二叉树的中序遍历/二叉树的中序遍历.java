package com.yly.algorithm.DFS.二叉树的中序遍历;

import com.yly.algorithm.DFS.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的中序遍历 {

    // dfs
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        getAns(root, ans);
        return ans;
    }

    private void getAns(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        getAns(node.left, ans);
        ans.add(node.val);
        getAns(node.right, ans);
    }

    /**
     * 迭代方式（其实就是使用堆栈模拟递归的遍历）
     * 好吧，中序遍历有点怪
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            output.add(root.val);
            root = root.right;
        }
        return output;
    }
}
