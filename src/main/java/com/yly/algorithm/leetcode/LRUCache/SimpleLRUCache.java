package com.yly.algorithm.leetcode.LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleLRUCache<K, V> extends LinkedHashMap<K, V> {

    private int cacheSize;

    /**
     * 传递进来最多能缓存多少数据
     */
    public SimpleLRUCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    /**
     * 判断元素个数是否超过缓存容量
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }
}

