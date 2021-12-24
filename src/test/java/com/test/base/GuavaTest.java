package com.test.base;

import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import com.dujinyue.bussiness.controller.GuavaController;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 高并发系统时有三把利器用来保护系统:
 * 缓存：缓存的目的是提升系统访问速度和增大系统处理容量
 * 降级：降级是当服务器压力剧增的情况下，根据当前业务情况及流量对一些服务和页面有策略的降级，以此释放服务器资源以保证核心任务的正常运行
 * 限流：限流的目的是通过对并发访问/请求进行限速，或者对一个时间窗口内的请求进行限速来保护系统，一旦达到限制速率则可以拒绝服务、排队或等待、降级等处理
 */
public class GuavaTest {
    /**
     * guava 实现的限流,这个是单机的限流,在分布式环境下不能直接这么用。
     * 如果我们能把permit放到Redis中就可以在分布式环境中用了。
     * <p>
     * {@link GuavaController}
     */
    @Test
    public void limitFlow() {
        //每秒两个
        RateLimiter limiter = RateLimiter.create(2.0);

        //tryAcquire尝试获取permit，默认超时时间是0，意思是拿不到就立即返回false
        boolean flag = limiter.tryAcquire();
        System.out.println(flag);
        double acquire = limiter.acquire();
        System.out.println(acquire);
    }

    /**
     * 当缓存失效时只穿透一个请求去访问后端
     * 对于同一个key，无论有多少请求，都只会允许一个线程去加载数据 ,这个是Guava cache的特性
     * 但是我们希望他 在去查询时不阻塞其他请求
     * https://blog.csdn.net/aitangyong/article/details/53504253  ???
     */
    @Test
//    public void test2() throws InterruptedException {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        对于同一个key，无论有多少请求，都只会允许一个线程去加载数据,其他阻塞
//        cache.put("name", "aty");
        //这个实现了查询时不阻塞其他请求(返回老的缓存值),只有查询的那个线程会返回新的值
        cache2.put("name", "aty");
        Thread.sleep(1500);
        for (int i = 0; i < 8; i++) {
            startThread(i, cache2);
        }
        // 让线程运行
        latch.countDown();
    }

    private static CountDownLatch latch = new CountDownLatch(1);

    // 1s无访问则缓存过期, 每次加载一个key需要耗时2s
    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return callable.call();
                }
            });

    // 1s后刷新缓存
    private static LoadingCache<String, String> cache2 = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return callable.call();
                }
            });

    // 模拟一个需要耗时2s的数据库查询任务
    private static Callable<String> callable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            System.out.println("begin to mock query db...");
            Thread.sleep(2000);
            System.out.println("success to mock query db...");
            return UUID.randomUUID().toString();
        }
    };

    private static void startThread(int id, LoadingCache cache) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "...begin");
                    latch.await();
                    Stopwatch watch = Stopwatch.createStarted();
                    System.out.println("value..." + cache.get("name"));
                    watch.stop();
                    System.out.println(Thread.currentThread().getName() + "...finish,cost time=" + watch.elapsed(TimeUnit.SECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.setName("Thread-" + id);
        t.start();
    }
}
