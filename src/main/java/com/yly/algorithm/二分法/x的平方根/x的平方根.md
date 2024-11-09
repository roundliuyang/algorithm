# x的平方根



## 题目描述

给你一个非负整数 `x` ，计算并返回 `x` 的 **算术平方根** 。

由于返回类型是整数，结果只保留 **整数部分** ，小数部分将被 **舍去 。**

**注意：**不允许使用任何内置指数函数和算符，例如 `pow(x, 0.5)` 或者 `x ** 0.5` 。

 

**示例 1：**

```
输入：x = 4
输出：2
```

**示例 2：**

```
输入：x = 8
输出：2
解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
```

 

**提示：**

- `0 <= x <= 231 - 1`



## 解题思路



## 代码

```java
public class x的平方根 {
    public int mySqrt(int x) {
        int L = 1, R = x;
        int ans = 0; //保存最后的解
        while (L <= R) {
            int mid = L + (R - L) / 2;
            long square = (long) mid * mid;
            if (square == x) {
                return mid;
            } else if (square < x) {
                ans = mid; //存起来以便返回
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return ans;
    }
}
```

