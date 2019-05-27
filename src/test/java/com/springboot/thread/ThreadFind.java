package com.springboot.thread;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * arraylist中有两万个，0-9的int型数据，怎么快速删除为2的
 * <p>
 * 多线程每个线程做一部分，并把结果合并起来（怎么实现）
 */
public class ThreadFind {

    private List<Integer> list = new ArrayList<>();

    @Before
    public void init() {
        for (int i = 0; i < 2000000; i++) {
            list.add(RandomUtils.nextInt(0, 10));
        }
    }

    @Test
    public void test() {
        //37804  200万个耗时  41031
        long startTime = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 2) {
                iterator.remove();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    @Test
    public void test2() throws InterruptedException, ExecutionException {
        //34937 没有想象中的效率提高?  40448
        //首先把list切分成小块,多线程每个线程做删除,最后在合并结果
        long startTime = System.currentTimeMillis();
        List<Integer> listTrue = new ArrayList<>();
        List<List<Integer>> lists = splitList(list, 20000);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (List<Integer> list : lists) {
            List<Integer> listTemp = threadFenDan(list,executorService);
            listTrue.addAll(listTemp);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println();
    }

    private List<Integer> threadFenDan(List<Integer> list,ExecutorService executorService) throws InterruptedException, ExecutionException {
        Future<List> submit = executorService.submit(new RemoveIntger2Thread(list));
        List list1 = submit.get();
        return list1;
    }

    /**
     * 把list平均成多少份
     * @param source
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n; //(先计算出余数)
        int number = source.size() / n; //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    private List<List<Integer>> splitList(List<Integer> list , int groupSize){
        return  Lists.partition(list, groupSize); // 使用guava
    }
}
