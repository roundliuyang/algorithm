package com.yly.algorithm.BFS.二叉树的层序遍历;

import com.yly.algorithm.BFS.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树的层序遍历 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            result.add(temp.val);
            if (temp.left != null) queue.offer(temp.left);
            if (temp.right != null) queue.offer(temp.right);
        }
        return result;
    }

    public List<List<Integer>> PrintFromTopToBottom2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            res.add(list);
        }
        return res;
    }
}








