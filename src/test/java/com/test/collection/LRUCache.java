package com.test.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 简单用LinkedHashMap来实现的LRU算法的缓存
 * LinkedHashMap 基于元素进入顺序和元素访问顺序排列 accessOrder
 *  它比 HashMap 多维护了一个双向链表，因此可以按照插入的顺序从头部或者从尾部迭代，是有序的，
 * TreeMap 是基于元素的固有顺序排列的（由 Comparator 或者 Comparable 确定）,TreeMap 的底层就是一颗红黑树
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int cacheSize;

    public LRUCache(int cacheSize) {
        super(16, (float) 0.75, true);
        this.cacheSize = cacheSize;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

    private static final Logger log = LoggerFactory.getLogger(LRUCache.class);

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(10);
        for (int i = 0; i < 10; i++) {
            cache.put("k" + i, i);
        }
        log.info("all cache :'{}'", cache);
        cache.get("k3");
        log.info("get k3 :'{}'", cache);
        cache.get("k4");
        log.info("get k4 :'{}'", cache);
        cache.get("k4");
        log.info("get k4 :'{}'", cache);
        cache.put("k" + 10, 10);
        log.info("After running the LRU algorithm cache :'{}'", cache);
    }
}
