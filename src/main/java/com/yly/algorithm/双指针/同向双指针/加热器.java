package com.yly.algorithm.双指针.同向双指针;

import java.util.Arrays;

public class 加热器 {
    /*
        给出两个数组：houses 和 heaters
        分别代表一个 一维坐标系上的 房屋坐标 和 加热器坐标
        你的任务是找到一个最小的加热半径 k
        保证所有房屋都能被加热器加热
        所有加热器的加热半径相同，均为k
        房屋和加热器坐标为正数，且不会超过 10^9
        输入：
        houses = [1,2,3,4] ,heaters = [1,4]
        输出： 1
     */


    /*
        解法一：二分法
        遍历每一个房屋 houses[i]
        二分查找 houses[i] 在 heaters 中的插入位置（j,j+1）
        比较两个加热器和房子的距离，选取更近的距离作为加热半径
        所有选取的加热半径的最大值即为答案。
        （单个房子先贪心一下取最小值，就只能但所有房子我们就只能取最大值）
        复杂度分析
        排序 heaters ,O(m * log m)
        遍历每一个房屋 house ,O(n)
        二分 house 在  heaters 中的插入位置,O(log m)
        总时间复杂度： O((n + m) * log m)
     */

    public int findRadius(int[] houses, int[] heaters) {
        // 二分插入位置需要数组有序
        Arrays.sort(heaters);
        // 最近距离的最大值为最小的加热半径
        int heatRadius = 0;
        // 遍历房屋找到最近的加热器距离
        for (int house : houses) {
            int radius = getMinimumRadius(house, heaters);
            heatRadius = Math.max(heatRadius, radius);
        }
        return heatRadius;
    }

    private int getMinimumRadius(int house, int[] heaters) {
        int left = 0;
        int right = heaters.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (heaters[mid] <= house) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // 在left 和 right 中找到答案
        int leftDistance = Math.abs(heaters[left] - house);
        int rightDistance = Math.abs(heaters[right] - house);
        return Math.min(leftDistance, rightDistance);
    }

    /*
        不会回头的指针
        假设 houses[i] 选择了heaters[j]
        那么houses[i+1] 一定不会选择 heaters[j-1]
        houses = [1,3,5,6,9],heaters = [2,8], k =3

        解法二：双数组型同向双指针
        与解法一的思路异曲同工，利用最近的两个加热器
        当亲的房子和最近两个加热器的距离中取到最小距离，最小距离的最大值就是答案

        两个数组最多被分别遍历一次 O（n+m）
        数组需要排序 O（n * log n + m * log m）
        总时间复杂度 O（n * log n + m* log m）
        空间复杂度  O(1)
     */

    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int n = houses.length;
        int m = heaters.length;
        int i = 0;
        int j = 0;
        int heatRadius = 0;
        while (i < n && j < m) {
            int  nowRadius = Math.abs(heaters[j]-houses[i]);
            int nextRadius = Integer.MAX_VALUE;
            if(j < m-1){
                nextRadius = Math.abs(heaters[j+1]- houses[i]);
            }
            if(nowRadius < nextRadius){
                heatRadius = Math.max(heatRadius, nowRadius);
                i++;
            }else{
                j++;
            }
        }
        return heatRadius;
    }
}
