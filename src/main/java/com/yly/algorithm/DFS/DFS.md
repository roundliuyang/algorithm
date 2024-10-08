# DFS



## DFS的三大高频考法

1. 树的分治和遍历
2. 求所有方案
3. 求排列组合



## DFS高频考法1——树的分治和遍历

必须掌握：树上的分治法 divide & conquer，前序preorder、中序 inorder、后序postorder遍历 

必须背诵：前序遍历和中序遍历的非递归(Iteration)版本





## DFS高频考法2—— 找所有方案

找到所有的方案

找到所有满足某个条件的具体方案
找到所有满足某个条件的方案总数





## DFS高频考法2——求排列组合

求出所有满足某个条件的排列或组合
求出某个满足条件的排列或组合
求出某个最优的排列或组合





## DFS vs BFS

凡是可以 BFS 的都要 BFS 而不应该使用 DFS

典型例子: 找连通的节点



## DFS 模板

我们可以使用以下模板简单地遍历树/图。



### 二叉树上的DFS

```java
// 深度优先搜索 (DFS)
public void dfs(TreeNode root) {
    if (root == null) {
        return;
    }
    // 处理当前节点
    doSomething(root);
    // 递归处理左子树
    dfs(root.left);
    // 递归处理右子树
    dfs(root.right);
}
```

