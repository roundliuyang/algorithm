# 最多有k个不同字符的最长子字符串



## 题目

给定字符串S，找到最多有k个不同字符的最长子串T。

**样例 1:**

```
输入: S = "eceba" 并且 k = 3
输出: 4
解释: T = "eceb"
```

**样例 2:**

```java
输入: S = "WORLD" 并且 k = 4
输出: 4
解释: T = "WORL" 或 "ORLD"
```



## 解题思路

1. **滑动窗口**：
   - `left` 和 `right` 分别表示当前窗口的左右边界，窗口内的字符是我们要考虑的子字符串。
   - `map` 用于记录当前窗口内每个字符的出现次数。`map.size()` 表示窗口内有多少不同的字符。
2. **移动右指针**：
   - 每次右指针 `right` 右移时，加入一个字符 `s[right]` 到窗口，并更新 `map` 记录字符的出现次数。
   - 如果窗口内的不同字符数量超过了 `k`，则需要移动左指针 `left`，直到窗口内的不同字符数小于等于 `k`。
3. **调整左指针**：
   - 当窗口内的不同字符数超过 `k` 时，移动左指针 `left`，并更新 `map` 中字符的出现次数。
   - 如果某个字符的出现次数减为 0，意味着这个字符已经不在窗口中，因此从 `map` 中移除该字符。
4. **记录最大长度**：
   - 每次窗口内不同字符数不超过 `k` 时，更新当前最长子字符串的长度 `res`。



### 关键点

1. **滑动窗口**：通过左右指针动态调整窗口大小，窗口内字符数量始终控制在 `k` 个不同字符。
2. **哈希表**：用 `HashMap` 记录窗口内字符及其出现次数，方便管理不同字符的数量。
3. **时间复杂度**：整个字符串只会遍历一次，时间复杂度为 O(n)，其中 `n` 是字符串的长度。

## 代码

```java
public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int left = 0;
    int right = 0;
    int n = s.length();
    int res = 0;
    HashMap<Character, Integer> map = new HashMap<>();

    while (right < n) {
        char rightChar = s.charAt(right);
        map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

        // 如果窗口内的不同字符数量超过 k，则移动左指针直到窗口内的不同字符数量不超过 k
        while (map.size() > k) {
            char leftChar = s.charAt(left);
            map.put(leftChar, map.get(leftChar) - 1);
            if (map.get(leftChar) == 0) {
                map.remove(leftChar);
            }
            left++;
        }
        // 更新结果
        res = Math.max(res, right - left + 1);
        right++;
    }
    return res;
}
```

