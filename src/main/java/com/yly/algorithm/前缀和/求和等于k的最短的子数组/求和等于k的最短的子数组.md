# 求和等于k的最短的子数组



## 题目

求和等于k的最短的子数组

## 解题思路

### 核心思想

通过**前缀和**来优化寻找子数组的过程。前缀和是指到当前为止所有元素的累计和，利用这个技巧可以快速计算任意一个子数组的和。公式如下： 

```
子数组和=prefixSum(j)−prefixSum(i) 
```

这意味着可以通过前缀和快速计算任意两个位置之间的子数组的和。



## 代码

```java
public class 求和等于k的最短的子数组 {

    // 时间复杂度O(n)
    public int subarraySumEqualsKII(int[] nums, int k) {
        int[] prefixSum = getPrefixSum(nums);

        int answer = Integer.MAX_VALUE;

        // 变量命名小技巧
        // hashmap / dict 用 key2value  的方式命名，代表了 key 是 sum, value 是 index
        HashMap<Integer, Integer> sum2index = new HashMap<> ();
        sum2index.put(0, 0);
        for (int end = 0; end < nums.length; end++) {
            if (sum2index.containsKey(prefixSum[end + 1] - k)) {
                int len = end + 1 - sum2index.get(prefixSum[end + 1] - k);
                answer = Math.min(answer, len);
            }
            sum2index.put(prefixSum[end + 1], end + 1);
        }
        return answer;
    }

    private int[] getPrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        return prefixSum;
    }

    /*
        拓展：求和= k的最长的子数组
        math.min--->max
        if(!sum2index.get(prefixSum[end + 1])){     //不存在才可以进行赋值，存在不能进行覆盖
         sum2index.put(prefixSum[end + 1], end + 1);
        }
     */
}
```



### 代码解释

1. 前缀和的计算
2.  寻找和为 `k` 的最短子数组
3.  逻辑细节
   - `sum2index` 是一个哈希表，用于存储某个前缀和 `prefixSum[i]` 以及它对应的索引 `i`。
     - 键 (`key`) 是前缀和 `prefixSum[i]`。
     - 值 (`value`) 是前缀和出现在数组中的索引位置 `i`。
   - 对于每个位置 `end`，我们计算当前前缀和 `prefixSum[end+1]`，并检查**是否存在某个前缀和 `prefixSum[i]`，使得 `prefixSum[end+1] - prefixSum[i] = k`**。如果存在，就说明从位置 `i` 到 `end` 的子数组和为 `k`。
   - 如果找到这样的子数组，更新答案 `answer`，保存最短的子数组长度。