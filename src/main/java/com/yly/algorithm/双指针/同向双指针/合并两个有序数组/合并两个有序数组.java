package com.yly.algorithm.双指针.同向双指针.合并两个有序数组;

public class 合并两个有序数组 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // 指向nums1的末尾有效元素
        int j = n - 1; // 指向nums2的末尾有效元素
        int k = m + n - 1; // 指向合并后的数组的末尾

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // 如果nums2中还有剩余元素未合并，则将其直接复制到nums1中
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }


    /**
     * 如果不是原地排序，直接new 一个新的数组就行了。类似合并两个有序链表。
     */
    public int[] merge3(int[] nums1, int m, int[] nums2, int n) {
        int[] mergedArray = new int[m + n];
        int i = m - 1; // 指向nums1的末尾有效元素
        int j = n - 1; // 指向nums2的末尾有效元素
        int k = m + n - 1; // 指向合并后的数组的末尾

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                mergedArray[k--] = nums1[i--];
            } else {
                mergedArray[k--] = nums2[j--];
            }
        }

        // 将nums1中剩余元素复制到mergedArray中
        while (i >= 0) {
            mergedArray[k--] = nums1[i--];
        }

        // 将nums2中剩余元素复制到mergedArray中
        while (j >= 0) {
            mergedArray[k--] = nums2[j--];
        }

        return mergedArray;
    }
}




 