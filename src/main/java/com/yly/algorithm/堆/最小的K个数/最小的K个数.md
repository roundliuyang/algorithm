# 最小的K个数



## 题目描述

设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。

**示例：**

```
输入： arr = [1,3,5,7,2,4,6,8], k = 4
输出： [1,2,3,4]
```

**提示：**

- `0 <= len(arr) <= 100000`
- `0 <= k <= min(100000, len(arr))`



## 解题思路





## 代码

```java
/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4
 * 大顶堆/小顶堆的解法 TODO TODO
 */
public class 最小的K个数 {
    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }
}
```

