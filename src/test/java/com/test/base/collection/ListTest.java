package com.test.base.collection;

import com.test.base.pojo.pojosort.Person;
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
     * 对list<pojo>去重 需要pojo 重写hashcode和equals
     */
    @Test
    public void testPojo() {
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
    public void fenpian() {
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


    private List<Integer> a = new ArrayList<>();
    private List<Integer> b = new ArrayList<>();

    private List<Stu> aPojos = new ArrayList();
    private List<Stu> bPojos = new ArrayList();

    class Stu {
        private String name;
        private int age;

        public Stu(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Stu)) return false;
            Stu stu = (Stu) o;
            return age == stu.age &&
                    Objects.equals(name, stu.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

    @Before
    public void init() {
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);

        b.add(2);
        b.add(3);
        b.add(6);
        b.add(8);

        aPojos.add(new Stu("w1", 21));
        aPojos.add(new Stu("w1", 21));
        aPojos.add(new Stu("w2", 21));
        aPojos.add(new Stu("w3", 21));
        aPojos.add(new Stu("w1", 22));

        bPojos.add(new Stu("w3", 21));
        bPojos.add(new Stu("w1", 21));
    }

    /**
     * A 和 B 的交集
     * [2, 3]
     */
    @Test
    public void 交集() {
        //a.retainAll(b);
        b.retainAll(a);
        System.out.println(b);

        aPojos.retainAll(bPojos);
        System.out.println(aPojos);
    }

    /**
     * A 和 B 的并集
     * [1, 2, 3, 4, 5, 6, 8]
     */
    @Test
    public void 并集() {
        a.addAll(b);
        Set<Integer> set = new LinkedHashSet<>(a);
        List<Integer> list = new ArrayList<>(set);
        System.out.println(list);

        aPojos.addAll(bPojos);
        Set<Stu> set2 = new LinkedHashSet<>(aPojos);
        List<Stu> list2 = new ArrayList<>(set2);
        System.out.println(list2);
    }

    /**
     * 求A的差集
     * [1, 4, 5]
     */
    @Test
    public void 差集() {
        b.retainAll(a);
        a.removeAll(b);
        System.out.println(a);

        bPojos.retainAll(aPojos);
        aPojos.removeAll(bPojos);
        System.out.println(aPojos);
    }

    /**
     * 要求拉链法求交集
     */
    @Test
    public void testZipperListIntersection() {
        String[] str = {"1", "3", "5", "7", "8", "9"};
        String[] str2 = {"2", "3", "4", "5", "5", "5"};
        List<String> list = Arrays.asList(str);
        List<String> list2 = Arrays.asList(str2);
        List<String> returnList = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        int count = 0;
        while (true) {
            count++;
            if (list.get(index1).equals(list2.get(index2))) {
                returnList.add(list.get(index1));
            } else {
                //指针较小的一个,加一,直到某个队列的队未,结束

            }
        }

//        System.out.println(returnList.toString());
//        System.out.println(count);
    }

    @Test
    public void testgg() {
        List<Stu> aPojos2 = this.aPojos;
        差集();
        System.out.println(this.aPojos);

    }
}
