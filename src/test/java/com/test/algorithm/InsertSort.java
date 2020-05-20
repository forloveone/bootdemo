package com.test.algorithm;

public class InsertSort {

    public static int[] insertSort(int[] source) {
        for (int i = 1; i < source.length; i++) {
            int key = source[i];
            int j = i - 1;
            while (j >= 0 && source[j] > key) {
                source[j + 1] = source[j];
                j = j - 1;
            }
            source[j + 1] = key;
        }
        return source;
    }

    public static void main(String[] args) {
        int[] ints2 = {5, 2, 4, 6, 1, 3};

        int[] temp2 = insertSort(ints2);
        System.out.println();
    }
}
