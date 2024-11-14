# 丑数II



## 题目描述

给你一个整数 `n` ，请你找出并返回第 `n` 个 **丑数** 。

**丑数** 就是质因子只包含 `2`、`3` 和 `5` 的正整数。

 

**示例 1：**

```
输入：n = 10
输出：12
解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
```

**示例 2：**

```
输入：n = 1
输出：1
解释：1 通常被视为丑数。
```

 

**提示：**

- `1 <= n <= 1690`



## 解题思路

**`i2, i3, i5` 指针**：

- `i2`、`i3` 和 `i5` 分别指向当前应该乘以 2、3 和 5 的丑数。每次计算时，从 `nums[i2] * 2`、`nums[i3] * 3` 和 `nums[i5] * 5` 中选择最小的一个作为下一个丑数，并将对应的指针加一，以保证该丑数的生成是按顺序的。

**`Math.min()`**：

- 每次计算新的丑数时，比较三个候选值：`nums[i2] * 2`、`nums[i3] * 3` 和 `nums[i5] * 5`，选择最小的一个作为下一个丑数。然后将对应的指针加一，以避免重复生成相同的丑数。

## 代码

```java
public class 丑数II {
    public static Ugly u = new Ugly();

    public int nthUglyNumber(int n) {
        return u.nums[n - 1];
    }
}

class Ugly {
    public int[] nums = new int[1690];

    Ugly() {
        nums[0] = 1;
        int ugly;
        int i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < 1690; ++i) {
            ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            nums[i] = ugly;

            if (ugly == nums[i2] * 2) ++i2;
            if (ugly == nums[i3] * 3) ++i3;
            if (ugly == nums[i5] * 5) ++i5;
        }
    }
}
```

