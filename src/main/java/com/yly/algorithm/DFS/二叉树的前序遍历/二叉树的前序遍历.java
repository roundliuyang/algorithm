package com.yly.algorithm.DFS.二叉树的前序遍历;

import com.yly.algorithm.DFS.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的前序遍历 {
    
    // dfs方式
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        helper(root, list);
        return list;
    }

    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        helper(root.left, list);
        helper(root.right, list);
    }

    // 迭代方式（其实就是使用堆栈模拟递归的遍历）
    public List<Integer> preorderTraversal2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
    }
}
