package com.springboot.jvm;

public class PringGC {
    /**
     * -XX:+PrintGC
     * -XX:+PrintGCDetails
     *  -Xloggc:D:/log/gc.log  如果使用这个参数会把GC
     *
     *  -XX:+TraceClassLoading 监控类的加载
     *
     * [GC (Allocation Failure)  32650K->2310K(123904K), 0.0096202 secs]
     * [GC (Allocation Failure)  34696K->2088K(156672K), 0.0075239 secs]
     * 发生了两次GC
     *
     *
     * [GC (Allocation Failure) [PSYoungGen: 32650K->2338K(37888K)] 32650K->2346K(123904K), 0.0028267 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
     * [GC (Allocation Failure) [PSYoungGen: 34725K->2088K(70656K)] 34733K->2104K(156672K), 0.0009963 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * Heap
     *  PSYoungGen      total 70656K, used 46941K [0x00000000d6100000, 0x00000000dab00000, 0x0000000100000000)
     *   eden space 65536K, 68% used [0x00000000d6100000,0x00000000d8ccd658,0x00000000da100000)
     *   from space 5120K, 40% used [0x00000000da600000,0x00000000da80a030,0x00000000dab00000)
     *   to   space 5120K, 0% used [0x00000000da100000,0x00000000da100000,0x00000000da600000)
     *  ParOldGen       total 86016K, used 16K [0x0000000082200000, 0x0000000087600000, 0x00000000d6100000)
     *   object space 86016K, 0% used [0x0000000082200000,0x0000000082204000,0x0000000087600000)
     *  Metaspace       used 3245K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 349K, capacity 388K, committed 512K, reserved 1048576K
     *
     *  堆分为 新生代 老年代 元空间
     *      新生代:
     *          伊甸区（eden）
     *          幸存区（from和to） 使用复制算法 所以总可用空间 70656K = eden + from(to)
     *
     *
     */
    public static void main(String[] args) {
        byte[] bytes = null;
        for (int i = 0; i < 100; i++) {
            bytes = new byte[1 * 1024 * 1024];
        }

        Runtime runtime = Runtime.getRuntime();
    }
}
