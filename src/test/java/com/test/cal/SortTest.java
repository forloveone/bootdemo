package com.test.cal;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 排序
 */
public class SortTest {
    private int[] ints = {34, 19, 11, 109, 3, 56};

    /**
     * 数组输出
     */
    private void printArray() {
        System.out.print("[");
        for (int x = 0; x < ints.length; x++) {
            if (x != ints.length - 1)
                System.out.print(ints[x] + ", ");
            else
                System.out.println(ints[x] + "]");
        }
    }

    /*
     * 数组翻转
     */
    public void reverseArray(int[] arr) {
        for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
            swap(arr, start, end);
        }
    }

    private int getIndex(int[] arr, int key) {
        for (int x = 0; x < arr.length; x++) {
            if (arr[x] == key)
                return x;
        }
        return -1;
    }

    private int getMax(int[] arr) {
        // 定义变量记录较大的值。
        int maxElement = arr[0];// 初始化为数组中的任意一个元素。
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > maxElement)
                maxElement = arr[x];
        }
        return maxElement;
    }

    private int getMax_2(int[] arr) {
        // 定义变量记录较大的值。
        int maxIndex = 0;// 初始化为数组中任意一个角标。
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > arr[maxIndex])
                maxIndex = x;
        }
        return arr[maxIndex];
    }

    /*
     * 选择排序
     * 首先在未排序的数列中找到最小(or最大)元素，然后将其存放到数列的起始位置；
     * 接着，再从剩余未排序的元素中继续寻找最小(or最大)元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕
     */
    public void selectionSort() {
        for (int x = 0; x < ints.length - 1; x++) {
            for (int y = x + 1; y < ints.length; y++) {
                if (ints[x] > ints[y]) {
                    swap(ints, x, y);
                }
            }
        }
    }

    /**
     * 工具类实现数组翻转
     */
    @Test
    public void reverse() {
        List temp = Arrays.asList(ints); //这样并不行
        List temp2 = new ArrayList();
        temp2.add("1");
        temp2.add("2");
        temp2.add("3");
        temp2.add("4");
        Collections.reverse(temp2);
        System.out.println(temp2);
    }

    /*
     * 冒泡排序,临近两个做比较
     * 遍历一趟的时间复杂度是O(N)，需要遍历N-1次  因此，冒泡排序的时间复杂度是O(N^2)
     */
    public void bubbleSort(int[] a) {
        for (int x = 0; x < a.length - 1; x++) {
            for (int y = 0; y < a.length - 1 - x; y++) {
                if (a[y] > a[y + 1]) {
                    swap(a, y, y + 1);
                }
            }
        }
    }

    /**
     * 冒泡排序改进版
     */
    public void bubbleSortGaijin(int[] a) {
        for (int x = 0; x < a.length - 1; x++) {
            int num = a[x];
            int index = x;
            for (int y = x + 1; y < a.length; y++) {
                if (num > a[y]) {
                    num = a[y];
                    index = y;
                }
            }
            if (index != x)
                swap(a, x, index);
        }
    }

    /*
     * 快速排序(使用分治法策略)
     * 选择一个基准数，通过一趟排序将要排序的数据分割成独立的两部分；
     *  其中一部分的所有数据都比另外一部分的所有数据都要小。
     *  然后，再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     *
     *  快速排序流程：
     *(1) 从数列中挑出一个基准值。
     *(2) 将所有比基准值小的摆放在基准前面，所有比基准值大的摆在基准的后面(相同的数可以到任一边)；在这个分区退出之后，
     *      该基准就处于数列的中间位置。
     *(3) 递归地把"基准值前面的子数列"和"基准值后面的子数列"进行排序。
     *
     * 快速排序的时间复杂度在最坏情况下是O(N2)，平均的时间复杂度是O(N*lgN)。
     *这句话很好理解：假设被排序的数列中有N个数。遍历一次的时间复杂度是O(N)，需要遍历多少次呢？至少lg(N+1)次，最多N次。
     *(01) 为什么最少是lg(N+1)次？快速排序是采用的分治法进行遍历的，我们将它看作一棵二叉树，它需要遍历的次数就是二叉树的深度，而根据完全二叉树的定义，它的深度至少是lg(N+1)。因此，快速排序的遍历次数最少是lg(N+1)次。
     *(02) 为什么最多是N次？这个应该非常简单，还是将快速排序看作一棵二叉树，它的深度最大是N。因此，快读排序的遍历次数最多是N次。
     *
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     l -- 数组的左边界(例如，从起始位置开始排序，则l=0)
     *     r -- 数组的右边界(例如，排序截至到数组末尾，则r=a.length-1)
     */
    public void quick_sort(int a[], int l, int r) {
        if (l < r) {
            int i, j, x;

            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while (i < j && a[j] > x)
                    j--; // 从右向左找第一个小于x的数
                if (i < j)
                    a[i++] = a[j];
                while (i < j && a[i] < x)
                    i++; // 从左向右找第一个大于x的数
                if (i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            quick_sort(a, l, i - 1); /* 递归调用 */
            quick_sort(a, i + 1, r); /* 递归调用 */
        }
    }

    /*
     * 直接插入排序
     *直接插入排序(Straight Insertion Sort)的基本思想是：
     * 把n个待排序的元素看成为一个有序表和一个无序表。
     * 开始时有序表中只包含1个元素，无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，
     * 将它插入到有序表中的适当位置，使之成为新的有序表，重复n-1次可完成排序过程。
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     n -- 数组的长度
     */
    public void insert_sort(int a[], int n) {
        int i, j, k;
        for (i = 1; i < n; i++) {
            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
            for (j = i - 1; j >= 0; j--) {
                if (a[j] < a[i]) {
                    break;
                }
            }
            //如找到了一个合适的位置
            if (j != i - 1) {
                //将比a[i]大的数据向后移
                int temp = a[i];
                for (k = i - 1; k > j; k--) {
                    a[k + 1] = a[k];
                }
                //将a[i]放到正确位置上
                a[k + 1] = temp;
            }
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    @Test
    public void bubbleSortTest() {
        printArray();
//        bubbleSort(ints);
//        bubbleSortGaijin(ints);
        insert_sort(ints, ints.length - 1);
        printArray();
    }

}
