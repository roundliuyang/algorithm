package com.yly.algorithm.同向双指针;

public class 合并两个有序数组 {
	 public void merge(int[] nums1, int m, int[] nums2, int n) {
		    int i = m - 1; //从末尾开始
		    int j = n - 1; //从末尾开始
		    int k = m + n - 1; //从末尾开始
		    while (j >= 0) {
		        if (i < 0) {
		            while (j >= 0) {
		                nums1[k--] = nums2[j--];
		            }
		            return;
		        }
		        //哪个数大就对应的添加哪个数。
		        if (nums1[i] > nums2[j]) {
		            nums1[k--] = nums1[i--];
		        } else {
		            nums1[k--] = nums2[j--];
		        }
		    }
	 }
	 // 如果不是原地排序，直接new 一个新的数组就行了。类似合并两个有序链表。
}




 