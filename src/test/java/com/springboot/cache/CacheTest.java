package com.springboot.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 分为本地缓存和redis
 */
public class CacheTest {

    /**
     * https://my.oschina.net/u/2270476/blog/1805749
     */
    @Test
    public void google() throws ExecutionException, InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)          //最大元素数目1000
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())         //并行度 允许并行修改的线程数 在一般情况下，将并发级别设置为服务器cpu核心数是一个比较不错的选择
                .expireAfterWrite(2, TimeUnit.MINUTES)        //写入后2分钟失效
                .build();
        cache.put("string", "1");
        // 获取一个缓存，如果该缓存不存在则返回一个null值
        String string = cache.getIfPresent("string");
        System.out.println(string);

//        Thread.sleep(2000);
        System.out.println(cache.getIfPresent("string"));

        // 获取缓存，当缓存不存在时，则通Callable进行加载并返回。该操作是原子
        String value = cache.get("k1", new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "not in cache";
            }
        });
        System.out.println(value);


        LoadingCache<String, Object> loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Object>() {
            @Override
            public Object load(String key) throws Exception {
                if (key.equals("k2")) {
                    System.out.println(key);
                    return key;
                }
                return null;
            }
        });
        // 获取缓存，当缓存不存在时，会通过CacheLoader自动加载，该方法会抛出ExecutionException异常
        loadingCache.get("k2");
        System.out.println(loadingCache.get("k2"));
    }

    @Test
    public void test(){
        CacheBuilder.newBuilder()
                // 设置缓存在写入10分钟后，通过CacheLoader的load方法进行刷新
                .refreshAfterWrite(10, TimeUnit.SECONDS)
                // jdk8以后可以使用 Duration
                // .refreshAfterWrite(Duration.ofMinutes(10))
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        // 缓存加载逻辑
                        return null;
                    }
                });
    }
}
