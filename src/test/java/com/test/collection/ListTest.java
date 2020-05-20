package com.test.collection;

import com.test.pojo.pojosort.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ListTest {
    private List<String> list = new ArrayList<>();

    @Before
    public void before() {
        list.add("women");
        list.add("zhangsan");
        list.add("women");
        list.add("lishi");
        list.add("wangwu ");
    }

    /**
     * 使用HashSet不能保证原有的list中的顺序
     */
    @Test
    public void test() {
        Set<String> set = new HashSet<>(list);
        List<String> list2 = new ArrayList<>(set);
        System.out.println(list2);
    }

    /**
     * 使用linkedHashSet可以保证原来的list中的顺序
     */
    @Test
    public void test2() {
        Set<String> set = new LinkedHashSet<>(list);
        List<String> list2 = new ArrayList<>(set);
        System.out.println(list2);
    }

    /**
     * 这个也可以去重
     * 频率分布
     */
    @Test
    public void test5() {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            if (Collections.frequency(result, s) < 1) {
                result.add(s);
            }
        }
        System.out.println(result);
    }

    /**
     * String[]数组去重,使用
     */
    @Test
    public void test3() {
        String[] str = {"16", "18", "20", "15", "20", "16", "17"};
        List<String> list = Arrays.asList(str);
        HashSet<String> hashSet = new HashSet<>(list);
        String[] str2 = hashSet.toArray(new String[hashSet.size() - 1]);
        for (String strs : str2) {
            System.out.println(strs);
        }
    }

    /**
     *  对list<pojo>去重 需要pojo 重写hashcode和equals
     */
    @Test
    public void testPojo(){
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person();
        p1.setAge(10);
        Person p2 = new Person();
        p2.setAge(11);
        Person p3 = new Person();
        p3.setAge(6);
        Person p4 = new Person();
        p4.setAge(6);
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);

        System.out.println(p1.compareTo(p2));
        System.out.println(p1.compareTo(p3));
        System.out.println(p3.compareTo(p1));
        Collections.sort(personList);
        System.out.println(personList);

        //去重后的list
        List<Person> listTrue = new ArrayList(new HashSet(personList));
        System.out.println(listTrue);
    }

    //判断数组中是否包含给定字符串
    public boolean useList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * Java8 stream 特性完成两个list int 相加
     */
    @Test
    public void test4() {
        List l1 = new ArrayList();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        List l2 = new ArrayList();
        l2.add(11);
        l2.add(12);
        l2.add(13);
        l2.add(14);

        List<Integer> result = IntStream.range(0, l1.size())
                .map(i -> (Integer) l1.get(i) + (Integer) l2.get(i))
                .boxed()
                .collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 迭代中remove元素
     * 循环删除list中多个元素的，应该使用迭代器iterator方式。
     */
    @Test
    public void removeObject() {
        //这种会导致 list的length改变
        for (int i = 0, length = list.size(); i < length; i++) {
            if (i == 1) {
                list.remove(i);
            }
        }
        System.out.println();

        // 这种会报错 ConcurrentModificationException
        for (String s : list) {
            if ("women".equals(s)) {
                list.remove(s);
                break; //如果只删除一个元素立刻 退出循环则不会报错
            }
        }
        System.out.println("123");

        // 最优解
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String temp = it.next();
            if ("women".equals(temp)) {
                it.remove();
            }
        }
    }

    /**
     * java 8 分片操作
     */
    @Test
    public void fenpian(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int limit = countStep(list.size());
        //方法一：使用流遍历操作
        List<List<Integer>> mglist = new ArrayList<>();
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            mglist.add(list.stream().skip(i * MAX_NUMBER).limit(MAX_NUMBER).collect(Collectors.toList()));
        });

        System.out.println(mglist);

        //方法二：获取分割后的集合
        List<List<Integer>> splitList =
                Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> list.stream().skip(a * MAX_NUMBER).limit(MAX_NUMBER).parallel().collect(Collectors.toList())).collect(Collectors.toList());

        System.out.println(splitList);

    }
    private static Integer countStep(Integer size) {
        return (size + MAX_NUMBER - 1) / MAX_NUMBER;
    }

    private static final Integer MAX_NUMBER = 3;

}
