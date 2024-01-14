package com.yly.algorithm.DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的后续遍历 {

    // dfs方式
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        helper(root, list);
        return list;
    }

    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        helper(root.left, list);
        helper(root.right, list);
        list.add(root.val);
    }


    // 迭代方式（其实就是使用堆栈模拟递归的遍历）
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }
}
