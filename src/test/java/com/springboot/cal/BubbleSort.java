package com.springboot.cal;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 排序
 */
public class BubbleSort {
	private int[] ints= { 34, 19, 11, 109, 3, 56 };

	/**
	 * 数组输出
	 */
	@Test
	public void printArray() {
		System.out.print("[");
		for (int x = 0; x < ints.length; x++) {
			if (x != ints.length - 1)
				System.out.print(ints[x] + ", ");
			else
				System.out.println(ints[x] + "]");
		}
	}

	/*
	 * 冒泡排序,临近两个做比较
	 */
	@Test
	public void bubbleSort() {
		for (int x = 0; x < ints.length - 1; x++) {
			for (int y = 0; y < ints.length - 1 - x; y++) {
				if (ints[y] > ints[y + 1]) {
					swap(ints, y, y + 1);
				}
			}
		}
		printArray();
	}

	/*
	 * 选择排序。第一个和后边所有的比较
	 */
	@Test
	public void selectSort() {
		for (int x = 0; x < ints.length - 1; x++) {
			for (int y = x + 1; y < ints.length; y++) {
				if (ints[x] > ints[y]) {
					swap(ints, x, y);
				}
			}
		}
		printArray();
	}

	public void selectSort_2() {
		for (int x = 0; x < ints.length - 1; x++) {
			int num = ints[x];
			int index = x;
			for (int y = x + 1; y < ints.length; y++) {
				if (num > ints[y]) {
					num = ints[y];
					index = y;
				}
			}
			if (index != x)
				swap(ints, x, index);
		}
	}

	/**
	 * 搜索前先排序,好像不排序也可以...
	 */
	@Test
	public void search(){
//		Arrays.sort(ints);
		int search = Arrays.binarySearch(ints,11);
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

	public int getIndex(int[] arr, int key) {
		for (int x = 0; x < arr.length; x++) {
			if (arr[x] == key)
				return x;
		}
		return -1;
	}

	public int getMax(int[] arr) {
		// 定义变量记录较大的值。
		int maxElement = arr[0];// 初始化为数组中的任意一个元素。
		for (int x = 1; x < arr.length; x++) {
			if (arr[x] > maxElement)
				maxElement = arr[x];
		}
		return maxElement;
	}

	public int getMax_2(int[] arr) {
		// 定义变量记录较大的值。
		int maxIndex = 0;// 初始化为数组中任意一个角标。
		for (int x = 1; x < arr.length; x++) {
			if (arr[x] > arr[maxIndex])
				maxIndex = x;
		}
		return arr[maxIndex];
	}

	/*
	 * 数组翻转
	 */
	public void reverseArray(int[] arr) {
		for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
			swap(arr, start, end);
		}
	}

	@Test
	public void reverse(){
		List temp = Arrays.asList(ints); //这样并不行
		List temp2 = new ArrayList();
		temp2.add("1");
		temp2.add("2");
		temp2.add("3");
		temp2.add("4");
		Collections.reverse(temp2);
		System.out.println(temp2);
	}

	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
