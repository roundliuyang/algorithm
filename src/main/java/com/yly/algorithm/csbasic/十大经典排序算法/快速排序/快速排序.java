package com.yly.algorithm.csbasic.十大经典排序算法.快速排序;

public class 快速排序 {
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int position = partition(array, low, high);
            quickSort(array, low, position - 1);
            quickSort(array, position + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        // 从序列中随机挑出一个元素，做为 “基准”(`pivot`)
        int pivot = array[high];
        int index = low;
        for (int i = low; i < high; i++) {
            if (array[i] <= pivot) {
                int temp = array[i];
                array[i] = array[index];
                array[index] = temp;
                index++;
            }
        }
        // 使基准处于数列的中间位置
        int temp = array[index];
        array[index] = array[high];
        array[high] = temp;
        return index;
    }
}
