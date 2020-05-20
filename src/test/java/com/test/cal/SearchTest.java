package com.test.cal;

import org.junit.Test;

import java.util.Arrays;

public class SearchTest {
    private int[] ints = {34, 19, 2, 11, 109, 3, 56};

    /**
     * 搜索前应先排序
     */
    @Test
    public void search() {
        Arrays.sort(ints);
        int search = Arrays.binarySearch(ints, 11);
        System.out.println(search);
    }

    /*
     * 二分法查找前要先拍好序
     */
    public int halfSearch(int[] arr, int key) {
        int max, min, mid;
        min = 0;
        max = arr.length - 1;
        mid = (max + min) / 2;
        while (arr[mid] != key) {
            if (key > arr[mid])
                min = mid + 1;
            else if (key < arr[mid])
                max = mid - 1;
            if (max < min)
                return -1;
            mid = (max + min) / 2;
        }
        return mid;
    }

    public int halfSearch_2(int[] arr, int key) {
        int max, min, mid;
        min = 0;
        max = arr.length - 1;
        while (min <= max) {
            mid = (max + min) >> 1;
            if (key > arr[mid])
                min = mid + 1;
            else if (key < arr[mid])
                max = mid - 1;
            else
                return mid;
        }
        return -min - 1;
    }
}
