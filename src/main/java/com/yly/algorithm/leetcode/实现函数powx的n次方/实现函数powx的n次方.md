# 实现函数powx的n次方

## 题目描述

实现 [pow(*x*, *n*)](https://www.cplusplus.com/reference/valarray/pow/) ，即计算 `x` 的整数 `n` 次幂函数（即，$x^n$ ）。

**示例 1：**

```
输入：x = 2.00000, n = 10
输出：1024.00000
```

**示例 2：**

```
输入：x = 2.10000, n = 3
输出：9.26100
```

**示例 3：**

```
输入：x = 2.00000, n = -2
输出：0.25000
解释：2-2 = 1/22 = 1/4 = 0.25
```

 

**提示：**

- `-100.0 < x < 100.0`
- $-2^{31} \leq n \leq 2^{31} - 1$
- `n` 是一个整数
- 要么 `x` 不为零，要么 `n > 0` 。
- $-10^4 \leq x^n \leq 10^4$

## 解题思路

**方法一：快速幂 + 递归** 

「快速幂算法」的本质是分治算法。举个例子，如果我们要计算 $x^{64}$，我们可以按照：
$$
x \rightarrow x^2 \rightarrow x^4 \rightarrow x^8 \rightarrow x^{16} \rightarrow x^{32} \rightarrow x^{64}
$$
的顺序，从 $x$ 开始，每次直接把上一次的结果进行平方，计算 6 次就可以得到 $x^{64}$ 的值，而不需要对 $x$ 乘 63 次 $x$。 再举一个例子，如果我们要计算 $x^{77}$​，我们可以按照：
$$
x \rightarrow x^2 \rightarrow x^4 \rightarrow x^9 \rightarrow x^{19} \rightarrow x^{38} \rightarrow x^{77}
$$
的顺序，在 $x \rightarrow x^2$，$x^2 \rightarrow x^4$，$x^{19} \rightarrow x^{38}$ 这些步骤中，我们直接把上一次的结果进行平方，而在 $x^4 \rightarrow x^9$，$x^9 \rightarrow x^{19}$，$x^{38} \rightarrow x^{77}$ 这些步骤中，我们把上一次的结果进行平方后，还要额外乘一个 $x$​。 

直接从左到右进行推导看上去很困难，因为在每一步中，我们不知道在将上一次的结果平方之后，还需不需要额外乘 $x$。但如果我们从右往左看，分治的思想就十分明显了： 

- 当我们要计算 $x^n$ 时，我们可以先递归地计算出 $y = x^{\lfloor n/2 \rfloor}$，其中 $\lfloor a \rfloor$ 表示对 $a$​ 进行下取整；
-  根据递归计算的结果，如果 $n$ 为偶数，那么 $x^n = y^2$；如果 $n$ 为奇数，那么 $x^n = y^2 \times x$。
-  递归的边界为 $n = 0$，任意数的 0 次方均为 1。 由于每次递归都会使得指数减少一半，因此递归的层数为 $O(\log n)$，算法可以在很快的时间内得到结果。



## 代码

```java
public class 实现函数powx的n次方 {
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
}
```

