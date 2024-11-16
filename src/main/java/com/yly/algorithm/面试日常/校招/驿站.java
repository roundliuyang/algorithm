package com.yly.algorithm.面试日常.校招;
import java.util.Scanner;
/**
	n 个客栈依次排列，给出 n - 1 条路的权重。从任意一处出发，每走过一条路，该路的权重减 1，但得到1 点利益。不能走权重为 0 的路。求最大利益。
	输入：
	5
	2 1 4 3
	输出
	9
 */
public class 驿站{
	public static void a02(int n,int[] nums){
	    //从第k个客栈出发，在客栈左边获得的利益。
	    int[] f_l = new int[n];
	    //从第k个客栈出发，在客栈右边获得的利益。
	    int[] f_r = new int[n];
	    //从第k个客栈出发，并且在k个客栈结束，在客栈左边获得的利益。
	    int[] g_l = new int[n];
	    //从第k个客栈出发，并在第k个客栈结束，在R_k中获得的最大利益
	    int[] g_r = new int[n];
	    f_l[0] = 0;
	    g_l[0] = 0;
	    for(int i=1;i<n;i++){
	        if(nums[i-1]%2 == 1){
	            f_l[i] = f_l[i-1]+nums[i-1];
	            //因为最后要回到出发点，所以只能遍历偶数次
	            g_l[i] = g_l[i-1]+nums[i-1]-1;
	        }else{
	            g_l[i] = g_l[i-1]+nums[i-1];
	            f_l[i] = Math.max(f_l[i-1]+nums[i-1]-1, g_l[i]);
	        }
	    }
	    for(int i=n-2;i>=0;i--){
	        if(nums[i]%2 == 1){
	            f_r[i] = f_r[i+1]+nums[i];
	            //因为最后要回到出发点，所以只能遍历偶数次
	            g_r[i] = g_r[i+1]+nums[i]-1;
	        }else{
	            g_r[i] = g_r[i+1]+nums[i];
	            f_r[i] = Math.max(f_r[i+1]+nums[i]-1, g_r[i]);
	        }
	    }
	    int max = Integer.MIN_VALUE;
	    for(int i=0;i<n;i++){
	        max = Math.max(Math.max(f_l[i]+g_r[i],f_r[i]+g_l[i]),max);
	    }
	    System.out.println(max);
	}
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    int n = scanner.nextInt();
	    int[] nums = new int[n-1];
	    for(int i=0;i<n-1;i++)
	        nums[i] = scanner.nextInt();
	    a02(n,nums);
	}
}




 