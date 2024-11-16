package com.yly.algorithm.面试日常.校招;
import java.util.Scanner;
/**
 * 给出n个物品，每个物品都有自己的价值，每个物品只有一件，这些物品需要分给两个人，
 * 要求分配完之后，两个人的物品价值相同。分配完成之后，会丢弃剩下的物品，求最少要丢弃多少物品。
        输入
        输入第一行为总的测试数据个数，第二行为物品个数n，第三行为n个物品的价值。
   1
   5
   30 60 5 15 30
       输出
   20 丢弃5和15，把60分配给第一个人，2个30分配给第二个人。
 */
public class 丢弃最少物品{
	static int res;
	public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
	    int t = scan.nextInt();
	    while(t-->0){
	        res = Integer.MAX_VALUE;
	        int n = scan.nextInt();
	        int[] item = new int[n];
	        for(int i =0;i<n;i++)
	            item[i] = scan.nextInt();
	        a3(item, 0,0,0, 0);
	        System.out.println(res);
	    }
	}
	public static void a3(int[] item,int index,int x,int y,int r){
	    if(index == item.length){
	        if(x == y)
	            res = Math.min(r,res);
	        return;
	    }
		a3(item,index+1,x+item[index],y,r);
	    a3(item,index+1,x,y+item[index],r);
	    a3(item,index+1,x,y,r+item[index]);
	}
}




 